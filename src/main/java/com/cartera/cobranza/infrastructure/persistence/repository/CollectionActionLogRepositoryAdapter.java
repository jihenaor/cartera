package com.cartera.cobranza.infrastructure.persistence.repository;

import com.cartera.cobranza.domain.model.CollectionActionLog;
import com.cartera.cobranza.domain.port.out.CollectionActionLogRepository;
import com.cartera.cobranza.infrastructure.persistence.entity.CollectionActionLogEntity;
import com.cartera.cobranza.infrastructure.persistence.mapper.CollectionActionLogEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CollectionActionLogRepositoryAdapter implements CollectionActionLogRepository {

    private final JpaCollectionActionLogRepository jpaCollectionActionLogRepository;
    private final CollectionActionLogEntityMapper mapper;
    private final JpaCollectionAssignmentRepository jpaCollectionAssignmentRepository;

    @Override
    public CollectionActionLog save(CollectionActionLog log) {
        CollectionActionLogEntity entity = mapper.toEntity(log);
        if (log.getAssignment() != null && log.getAssignment().getId() != null) {
            entity.setAssignment(jpaCollectionAssignmentRepository.getReferenceById(log.getAssignment().getId()));
        }
        CollectionActionLogEntity saved = jpaCollectionActionLogRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<CollectionActionLog> findByAssignment(UUID assignmentId) {
        return jpaCollectionActionLogRepository.findByAssignmentId(assignmentId).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
