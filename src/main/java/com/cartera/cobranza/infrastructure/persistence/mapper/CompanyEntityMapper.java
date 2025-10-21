package com.cartera.cobranza.infrastructure.persistence.mapper;

import com.cartera.cobranza.domain.model.Company;
import com.cartera.cobranza.infrastructure.persistence.entity.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class CompanyEntityMapper {

    public CompanyEntity toEntity(Company company) {
        if (company == null) {
            return null;
        }
        CompanyEntity entity = new CompanyEntity();
        entity.setId(company.getId());
        entity.setNit(company.getNit());
        entity.setName(company.getName());
        entity.setEmail(company.getEmail());
        entity.setPhone(company.getPhone());
        entity.setAddress(company.getAddress());
        entity.setActive(company.isActive());
        return entity;
    }

    public Company toDomain(CompanyEntity entity) {
        if (entity == null) {
            return null;
        }
        return Company.builder()
                .id(entity.getId())
                .nit(entity.getNit())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .active(entity.isActive())
                .build();
    }
}
