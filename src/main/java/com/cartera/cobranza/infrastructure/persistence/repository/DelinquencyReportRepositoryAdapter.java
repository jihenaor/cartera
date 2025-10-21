package com.cartera.cobranza.infrastructure.persistence.repository;

import com.cartera.cobranza.domain.model.DelinquencyReportEntry;
import com.cartera.cobranza.domain.port.out.DelinquencyReportRepository;
import com.cartera.cobranza.infrastructure.persistence.entity.DelinquencyReportEntity;
import com.cartera.cobranza.infrastructure.persistence.mapper.DelinquencyReportEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DelinquencyReportRepositoryAdapter implements DelinquencyReportRepository {

    private final JpaDelinquencyReportRepository jpaDelinquencyReportRepository;
    private final DelinquencyReportEntityMapper mapper;

    @Override
    public DelinquencyReportEntry save(DelinquencyReportEntry entry) {
        DelinquencyReportEntity entity = mapper.toEntity(entry);
        DelinquencyReportEntity saved = jpaDelinquencyReportRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<DelinquencyReportEntry> findById(UUID id) {
        return jpaDelinquencyReportRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<DelinquencyReportEntry> findByReportPeriod(YearMonth period) {
        return jpaDelinquencyReportRepository
                .findByReportYearAndReportMonth(period.getYear(), period.getMonthValue())
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<DelinquencyReportEntry> findByCompany(UUID companyId) {
        return jpaDelinquencyReportRepository.findByCompanyId(companyId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
