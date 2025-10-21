package com.cartera.cobranza.application.usecase;

import com.cartera.cobranza.domain.model.Company;
import com.cartera.cobranza.domain.model.DelinquencyRecord;
import com.cartera.cobranza.domain.model.DelinquencyStatus;
import com.cartera.cobranza.domain.port.in.PortfolioSelectionUseCase;
import com.cartera.cobranza.domain.port.out.CompanyRepository;
import com.cartera.cobranza.domain.port.out.DelinquencyRecordRepository;
import com.cartera.cobranza.domain.port.out.ExternalDelinquencySource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioSelectionService implements PortfolioSelectionUseCase {

    private final ExternalDelinquencySource externalDelinquencySource;
    private final DelinquencyRecordRepository delinquencyRecordRepository;
    private final CompanyRepository companyRepository;

    @Override
    public List<DelinquencyRecord> registerMonthlyDelinquency(int year, int month) {
        List<DelinquencyRecord> records = externalDelinquencySource.fetchDelinquentCompaniesForPeriod(year, month);
        List<DelinquencyRecord> persisted = new ArrayList<>();
        YearMonth period = YearMonth.of(year, month);
        for (DelinquencyRecord record : records) {
            Company company = resolveCompany(record.getCompany());
            record.setCompany(company);
            record.setPeriod(period);
            record.setStatus(DelinquencyStatus.OPEN);
            record.setRegisteredAt(LocalDateTime.now());
            boolean exists = !delinquencyRecordRepository.findByCompanyAndPeriod(company.getId(), period).isEmpty();
            if (exists) {
                continue;
            }
            persisted.add(delinquencyRecordRepository.save(record));
        }
        return persisted;
    }

    private Company resolveCompany(Company company) {
        return companyRepository.findByNit(company.getNit())
                .map(existing -> {
                    existing.setName(company.getName());
                    existing.setEmail(company.getEmail());
                    existing.setPhone(company.getPhone());
                    existing.setAddress(company.getAddress());
                    existing.setActive(true);
                    return companyRepository.save(existing);
                })
                .orElseGet(() -> companyRepository.save(company));
    }
}
