package com.cartera.cobranza.application.usecase;

import com.cartera.cobranza.domain.model.DelinquencyReportEntry;
import com.cartera.cobranza.domain.port.in.DelinquencyReportUseCase;
import com.cartera.cobranza.domain.port.out.DelinquencyReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class DelinquencyReportService implements DelinquencyReportUseCase {

    private final DelinquencyReportRepository delinquencyReportRepository;

    @Override
    public DelinquencyReportEntry registerReportEntry(DelinquencyReportEntry entry) {
        if (entry.getTotalAmount() == null && entry.getPrincipalAmount() != null && entry.getInterestAmount() != null) {
            entry.setTotalAmount(entry.getPrincipalAmount().add(entry.getInterestAmount()));
        }
        LocalDateTime now = LocalDateTime.now();
        if (entry.getGeneratedAt() == null) {
            entry.setGeneratedAt(now);
        }
        entry.setUpdatedAt(now);
        return delinquencyReportRepository.save(entry);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DelinquencyReportEntry> findById(UUID id) {
        return delinquencyReportRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DelinquencyReportEntry> findByReportPeriod(YearMonth period) {
        return delinquencyReportRepository.findByReportPeriod(period);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DelinquencyReportEntry> findByCompany(UUID companyId) {
        return delinquencyReportRepository.findByCompany(companyId);
    }
}
