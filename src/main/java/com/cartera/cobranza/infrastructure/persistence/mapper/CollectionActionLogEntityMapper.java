package com.cartera.cobranza.infrastructure.persistence.mapper;

import com.cartera.cobranza.domain.model.CollectionActionLog;
import com.cartera.cobranza.infrastructure.persistence.entity.CollectionActionLogEntity;
import org.springframework.stereotype.Component;

@Component
public class CollectionActionLogEntityMapper {

    private final CollectionAssignmentEntityMapper assignmentEntityMapper;

    public CollectionActionLogEntityMapper(CollectionAssignmentEntityMapper assignmentEntityMapper) {
        this.assignmentEntityMapper = assignmentEntityMapper;
    }

    public CollectionActionLogEntity toEntity(CollectionActionLog log) {
        if (log == null) {
            return null;
        }
        CollectionActionLogEntity entity = new CollectionActionLogEntity();
        entity.setId(log.getId());
        entity.setAssignment(assignmentEntityMapper.toEntity(log.getAssignment()));
        entity.setActionDate(log.getActionDate());
        entity.setActionType(log.getActionType());
        entity.setContactChannel(log.getContactChannel());
        entity.setComments(log.getComments());
        entity.setCreatedBy(log.getCreatedBy());
        entity.setPaymentReceived(log.isPaymentReceived());
        entity.setPaymentAmount(log.getPaymentAmount());
        entity.setPaymentDate(log.getPaymentDate());
        return entity;
    }

    public CollectionActionLog toDomain(CollectionActionLogEntity entity) {
        if (entity == null) {
            return null;
        }
        return CollectionActionLog.builder()
                .id(entity.getId())
                .assignment(assignmentEntityMapper.toDomain(entity.getAssignment()))
                .actionDate(entity.getActionDate())
                .actionType(entity.getActionType())
                .contactChannel(entity.getContactChannel())
                .comments(entity.getComments())
                .createdBy(entity.getCreatedBy())
                .paymentReceived(entity.isPaymentReceived())
                .paymentAmount(entity.getPaymentAmount())
                .paymentDate(entity.getPaymentDate())
                .build();
    }
}
