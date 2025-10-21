package com.cartera.cobranza.infrastructure.persistence.repository;

import com.cartera.cobranza.domain.model.CollectionManager;
import com.cartera.cobranza.domain.port.out.CollectionManagerRepository;
import com.cartera.cobranza.infrastructure.persistence.entity.CollectionManagerEntity;
import com.cartera.cobranza.infrastructure.persistence.mapper.CollectionManagerEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CollectionManagerRepositoryAdapter implements CollectionManagerRepository {

    private final JpaCollectionManagerRepository jpaCollectionManagerRepository;
    private final CollectionManagerEntityMapper entityMapper;

    @Override
    public CollectionManager save(CollectionManager manager) {
        CollectionManagerEntity entity = entityMapper.toEntity(manager);
        CollectionManagerEntity saved = jpaCollectionManagerRepository.save(entity);
        return entityMapper.toDomain(saved);
    }

    @Override
    public Optional<CollectionManager> findById(UUID id) {
        return jpaCollectionManagerRepository.findById(id)
                .map(entityMapper::toDomain);
    }

    @Override
    public Optional<CollectionManager> findByCode(String code) {
        return jpaCollectionManagerRepository.findByCode(code)
                .map(entityMapper::toDomain);
    }

    @Override
    public List<CollectionManager> findAllActive() {
        return jpaCollectionManagerRepository.findAll().stream()
                .filter(CollectionManagerEntity::isActive)
                .map(entityMapper::toDomain)
                .toList();
    }
}
