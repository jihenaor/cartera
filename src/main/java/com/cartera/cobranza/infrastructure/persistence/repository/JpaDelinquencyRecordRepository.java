package com.cartera.cobranza.infrastructure.persistence.repository;

import com.cartera.cobranza.domain.model.DelinquencyStatus;
import com.cartera.cobranza.infrastructure.persistence.entity.DelinquencyRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaDelinquencyRecordRepository extends JpaRepository<DelinquencyRecordEntity, UUID> {

    List<DelinquencyRecordEntity> findByCompanyIdAndPeriodYearAndPeriodMonth(UUID companyId, int periodYear, int periodMonth);

    List<DelinquencyRecordEntity> findByStatus(DelinquencyStatus status);
}
