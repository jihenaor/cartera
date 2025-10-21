package com.cartera.cobranza.infrastructure.persistence.mapper;

import com.cartera.cobranza.domain.model.CollectionAssignment;
import com.cartera.cobranza.infrastructure.persistence.entity.CollectionAssignmentEntity;
import org.springframework.stereotype.Component;

@Component
public class CollectionAssignmentEntityMapper {

    private final DelinquencyRecordEntityMapper delinquencyRecordEntityMapper;
    private final CollectionManagerEntityMapper managerEntityMapper;

    public CollectionAssignmentEntityMapper(DelinquencyRecordEntityMapper delinquencyRecordEntityMapper,
                                            CollectionManagerEntityMapper managerEntityMapper) {
        this.delinquencyRecordEntityMapper = delinquencyRecordEntityMapper;
        this.managerEntityMapper = managerEntityMapper;
    }

    public CollectionAssignmentEntity toEntity(CollectionAssignment assignment) {
        if (assignment == null) {
            return null;
        }
        CollectionAssignmentEntity entity = new CollectionAssignmentEntity();
        entity.setId(assignment.getId());
        entity.setDelinquencyRecord(delinquencyRecordEntityMapper.toEntity(assignment.getDelinquencyRecord()));
        entity.setManager(managerEntityMapper.toEntity(assignment.getManager()));
        entity.setAssignedDate(assignment.getAssignedDate());
        entity.setStatus(assignment.getStatus());
        entity.setObservations(assignment.getObservations());
        return entity;
    }

    public CollectionAssignment toDomain(CollectionAssignmentEntity entity) {
        if (entity == null) {
            return null;
        }
        return CollectionAssignment.builder()
                .id(entity.getId())
                .delinquencyRecord(delinquencyRecordEntityMapper.toDomain(entity.getDelinquencyRecord()))
                .manager(managerEntityMapper.toDomain(entity.getManager()))
                .assignedDate(entity.getAssignedDate())
                .status(entity.getStatus())
                .observations(entity.getObservations())
                .build();
    }
}
