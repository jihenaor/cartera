package com.cartera.cobranza.infrastructure.persistence.repository;

import com.cartera.cobranza.domain.model.DelinquencyRecord;
import com.cartera.cobranza.domain.model.DelinquencyStatus;
import com.cartera.cobranza.domain.port.out.DelinquencyRecordRepository;
import com.cartera.cobranza.infrastructure.persistence.entity.CompanyEntity;
import com.cartera.cobranza.infrastructure.persistence.entity.DelinquencyRecordEntity;
import com.cartera.cobranza.infrastructure.persistence.mapper.DelinquencyRecordEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DelinquencyRecordRepositoryAdapter implements DelinquencyRecordRepository {

    private final JpaDelinquencyRecordRepository jpaDelinquencyRecordRepository;
    private final DelinquencyRecordEntityMapper mapper;
    private final JpaCompanyRepository jpaCompanyRepository;

    @Override
    public DelinquencyRecord save(DelinquencyRecord record) {
        DelinquencyRecordEntity entity = mapper.toEntity(record);
        if (record.getCompany() != null && record.getCompany().getId() != null) {
            CompanyEntity companyReference = jpaCompanyRepository.getReferenceById(record.getCompany().getId());
            entity.setCompany(companyReference);
        }
        DelinquencyRecordEntity saved = jpaDelinquencyRecordRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<DelinquencyRecord> findById(UUID id) {
        return jpaDelinquencyRecordRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<DelinquencyRecord> findByCompanyAndPeriod(UUID companyId, YearMonth period) {
        return jpaDelinquencyRecordRepository
                .findByCompanyIdAndPeriodYearAndPeriodMonth(companyId, period.getYear(), period.getMonthValue())
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<DelinquencyRecord> findByStatus(DelinquencyStatus status) {
        return jpaDelinquencyRecordRepository.findByStatus(status).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
