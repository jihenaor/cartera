package com.cartera.cobranza.infrastructure.adapter.web.controller;

import com.cartera.cobranza.domain.model.Company;
import com.cartera.cobranza.domain.port.in.CompanyManagementUseCase;
import com.cartera.cobranza.infrastructure.adapter.web.dto.CompanyRequest;
import com.cartera.cobranza.infrastructure.adapter.web.dto.CompanyResponse;
import com.cartera.cobranza.infrastructure.adapter.web.mapper.WebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
@Validated
public class CompanyController {

    private final CompanyManagementUseCase companyManagementUseCase;
    private final WebMapper webMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse createCompany(@Valid @RequestBody CompanyRequest request) {
        Company company = webMapper.toDomain(request);
        Company saved = companyManagementUseCase.registerOrUpdateCompany(company);
        return webMapper.toResponse(saved);
    }

    @PutMapping("/{id}")
    public CompanyResponse updateCompany(@PathVariable UUID id, @Valid @RequestBody CompanyRequest request) {
        Company company = webMapper.toDomain(request);
        company.setId(id);
        Company updated = companyManagementUseCase.registerOrUpdateCompany(company);
        return webMapper.toResponse(updated);
    }

    @GetMapping
    public List<CompanyResponse> listActiveCompanies() {
        return companyManagementUseCase.listActiveCompanies().stream()
                .map(webMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public CompanyResponse getCompany(@PathVariable UUID id) {
        Company company = companyManagementUseCase.getCompany(id);
        return webMapper.toResponse(company);
    }
}
