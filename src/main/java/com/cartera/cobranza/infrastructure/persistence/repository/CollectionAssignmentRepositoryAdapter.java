package com.cartera.cobranza.infrastructure.persistence.repository;

import com.cartera.cobranza.domain.model.AssignmentStatus;
import com.cartera.cobranza.domain.model.CollectionAssignment;
import com.cartera.cobranza.domain.port.out.CollectionAssignmentRepository;
import com.cartera.cobranza.infrastructure.persistence.entity.CollectionAssignmentEntity;
import com.cartera.cobranza.infrastructure.persistence.mapper.CollectionAssignmentEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CollectionAssignmentRepositoryAdapter implements CollectionAssignmentRepository {

    private final JpaCollectionAssignmentRepository jpaCollectionAssignmentRepository;
    private final CollectionAssignmentEntityMapper mapper;
    private final JpaDelinquencyRecordRepository jpaDelinquencyRecordRepository;
    private final JpaCollectionManagerRepository jpaCollectionManagerRepository;

    @Override
    public CollectionAssignment save(CollectionAssignment assignment) {
        CollectionAssignmentEntity entity = mapper.toEntity(assignment);
        if (assignment.getDelinquencyRecord() != null && assignment.getDelinquencyRecord().getId() != null) {
            entity.setDelinquencyRecord(jpaDelinquencyRecordRepository.getReferenceById(assignment.getDelinquencyRecord().getId()));
        }
        if (assignment.getManager() != null && assignment.getManager().getId() != null) {
            entity.setManager(jpaCollectionManagerRepository.getReferenceById(assignment.getManager().getId()));
        }
        CollectionAssignmentEntity saved = jpaCollectionAssignmentRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<CollectionAssignment> findById(UUID id) {
        return jpaCollectionAssignmentRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<CollectionAssignment> findByManagerAndStatus(UUID managerId, AssignmentStatus status) {
        return jpaCollectionAssignmentRepository.findByManagerIdAndStatus(managerId, status).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<CollectionAssignment> findByPeriod(YearMonth period) {
        return jpaCollectionAssignmentRepository
                .findByDelinquencyRecord_PeriodYearAndDelinquencyRecord_PeriodMonth(period.getYear(), period.getMonthValue())
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
