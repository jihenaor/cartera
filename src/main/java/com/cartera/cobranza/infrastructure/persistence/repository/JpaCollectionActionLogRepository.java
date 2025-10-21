package com.cartera.cobranza.infrastructure.persistence.repository;

import com.cartera.cobranza.infrastructure.persistence.entity.CollectionActionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaCollectionActionLogRepository extends JpaRepository<CollectionActionLogEntity, UUID> {

    List<CollectionActionLogEntity> findByAssignmentId(UUID assignmentId);
}
