package com.cartera.cobranza.infrastructure.persistence.repository;

import com.cartera.cobranza.infrastructure.persistence.entity.DelinquencyReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaDelinquencyReportRepository extends JpaRepository<DelinquencyReportEntity, UUID> {

    List<DelinquencyReportEntity> findByReportYearAndReportMonth(int reportYear, int reportMonth);

    List<DelinquencyReportEntity> findByCompanyId(UUID companyId);
}
