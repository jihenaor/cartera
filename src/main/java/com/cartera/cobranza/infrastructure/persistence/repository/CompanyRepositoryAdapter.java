package com.cartera.cobranza.infrastructure.persistence.repository;

import com.cartera.cobranza.domain.model.Company;
import com.cartera.cobranza.domain.port.out.CompanyRepository;
import com.cartera.cobranza.infrastructure.persistence.entity.CompanyEntity;
import com.cartera.cobranza.infrastructure.persistence.mapper.CompanyEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CompanyRepositoryAdapter implements CompanyRepository {

    private final JpaCompanyRepository jpaCompanyRepository;
    private final CompanyEntityMapper companyEntityMapper;

    @Override
    public Company save(Company company) {
        CompanyEntity entity = companyEntityMapper.toEntity(company);
        CompanyEntity saved = jpaCompanyRepository.save(entity);
        return companyEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Company> findById(UUID id) {
        return jpaCompanyRepository.findById(id)
                .map(companyEntityMapper::toDomain);
    }

    @Override
    public Optional<Company> findByNit(String nit) {
        return jpaCompanyRepository.findByNit(nit)
                .map(companyEntityMapper::toDomain);
    }

    @Override
    public List<Company> findAllActive() {
        return jpaCompanyRepository.findAll().stream()
                .filter(CompanyEntity::isActive)
                .map(companyEntityMapper::toDomain)
                .toList();
    }
}
