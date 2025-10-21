package com.cartera.cobranza.infrastructure.adapter.web.controller;

import com.cartera.cobranza.domain.model.Company;
import com.cartera.cobranza.domain.port.in.CompanyManagementUseCase;
import com.cartera.cobranza.domain.port.in.DelinquencyReportUseCase;
import com.cartera.cobranza.infrastructure.adapter.web.dto.DelinquencyReportRequest;
import com.cartera.cobranza.infrastructure.adapter.web.dto.DelinquencyReportResponse;
import com.cartera.cobranza.infrastructure.adapter.web.mapper.WebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/delinquency-reports")
@RequiredArgsConstructor
public class DelinquencyReportController {

    private final DelinquencyReportUseCase delinquencyReportUseCase;
    private final CompanyManagementUseCase companyManagementUseCase;
    private final WebMapper webMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DelinquencyReportResponse create(@Valid @RequestBody DelinquencyReportRequest request) {
        Company company;
        try {
            company = companyManagementUseCase.getCompany(request.getCompanyId());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
        return webMapper.toResponse(
                delinquencyReportUseCase.registerReportEntry(webMapper.toDomain(request, company))
        );
    }

    @GetMapping("/{id}")
    public DelinquencyReportResponse findById(@PathVariable UUID id) {
        return delinquencyReportUseCase.findById(id)
                .map(webMapper::toResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reporte de mora no encontrado"));
    }

    @GetMapping
    public List<DelinquencyReportResponse> findByPeriod(
            @RequestParam("period") @DateTimeFormat(pattern = "yyyy-MM") YearMonth period) {
        return delinquencyReportUseCase.findByReportPeriod(period)
                .stream()
                .map(webMapper::toResponse)
                .toList();
    }

    @GetMapping("/company/{companyId}")
    public List<DelinquencyReportResponse> findByCompany(@PathVariable UUID companyId) {
        return delinquencyReportUseCase.findByCompany(companyId)
                .stream()
                .map(webMapper::toResponse)
                .toList();
    }
}
