package com.cartera.cobranza.infrastructure.persistence.mapper;

import com.cartera.cobranza.domain.model.CollectionManager;
import com.cartera.cobranza.infrastructure.persistence.entity.CollectionManagerEntity;
import org.springframework.stereotype.Component;

@Component
public class CollectionManagerEntityMapper {

    public CollectionManagerEntity toEntity(CollectionManager manager) {
        if (manager == null) {
            return null;
        }
        CollectionManagerEntity entity = new CollectionManagerEntity();
        entity.setId(manager.getId());
        entity.setCode(manager.getCode());
        entity.setFullName(manager.getFullName());
        entity.setEmail(manager.getEmail());
        entity.setPhone(manager.getPhone());
        entity.setActive(manager.isActive());
        return entity;
    }

    public CollectionManager toDomain(CollectionManagerEntity entity) {
        if (entity == null) {
            return null;
        }
        return CollectionManager.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .fullName(entity.getFullName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .active(entity.isActive())
                .build();
    }
}
