package com.cartera.cobranza.infrastructure.persistence.repository;

import com.cartera.cobranza.infrastructure.persistence.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaCompanyRepository extends JpaRepository<CompanyEntity, UUID> {

    Optional<CompanyEntity> findByNit(String nit);
}
