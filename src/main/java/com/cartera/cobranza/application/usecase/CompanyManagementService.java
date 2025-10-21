package com.cartera.cobranza.application.usecase;

import com.cartera.cobranza.domain.model.Company;
import com.cartera.cobranza.domain.port.in.CompanyManagementUseCase;
import com.cartera.cobranza.domain.port.out.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyManagementService implements CompanyManagementUseCase {

    private final CompanyRepository companyRepository;

    @Override
    public Company registerOrUpdateCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company getCompany(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada: " + id));
    }

    @Override
    public List<Company> listActiveCompanies() {
        return companyRepository.findAllActive();
    }
}
