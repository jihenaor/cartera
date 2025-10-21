package com.cartera.cobranza.infrastructure.persistence.repository;

import com.cartera.cobranza.domain.model.AssignmentStatus;
import com.cartera.cobranza.infrastructure.persistence.entity.CollectionAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaCollectionAssignmentRepository extends JpaRepository<CollectionAssignmentEntity, UUID> {

    List<CollectionAssignmentEntity> findByManagerIdAndStatus(UUID managerId, AssignmentStatus status);

    List<CollectionAssignmentEntity> findByDelinquencyRecord_PeriodYearAndDelinquencyRecord_PeriodMonth(int periodYear, int periodMonth);
}
