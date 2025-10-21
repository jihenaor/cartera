package com.cartera.cobranza.infrastructure.persistence.repository;

import com.cartera.cobranza.infrastructure.persistence.entity.CollectionManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaCollectionManagerRepository extends JpaRepository<CollectionManagerEntity, UUID> {

    Optional<CollectionManagerEntity> findByCode(String code);
}
