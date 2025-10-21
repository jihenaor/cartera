package com.cartera.cobranza.infrastructure.adapter.web.controller;

import com.cartera.cobranza.domain.model.Company;
import com.cartera.cobranza.domain.port.in.CompanyManagementUseCase;
import com.cartera.cobranza.domain.port.in.DelinquencyManagementUseCase;
import com.cartera.cobranza.domain.port.in.PortfolioSelectionUseCase;
import com.cartera.cobranza.infrastructure.adapter.web.dto.DelinquencyRecordRequest;
import com.cartera.cobranza.infrastructure.adapter.web.dto.DelinquencyRecordResponse;
import com.cartera.cobranza.infrastructure.adapter.web.dto.MonthlySelectionRequest;
import com.cartera.cobranza.infrastructure.adapter.web.mapper.WebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/delinquencies")
@RequiredArgsConstructor
public class DelinquencyController {

    private final DelinquencyManagementUseCase delinquencyManagementUseCase;
    private final PortfolioSelectionUseCase portfolioSelectionUseCase;
    private final CompanyManagementUseCase companyManagementUseCase;
    private final WebMapper webMapper;

    @PostMapping("/manual")
    @ResponseStatus(HttpStatus.CREATED)
    public DelinquencyRecordResponse createManualRecord(@Valid @RequestBody DelinquencyRecordRequest request) {
        Company company = companyManagementUseCase.getCompany(request.getCompanyId());
        var record = webMapper.toDomain(request, company);
        var saved = delinquencyManagementUseCase.createManualRecord(record);
        return webMapper.toResponse(saved);
    }

    @PostMapping("/selection")
    public List<DelinquencyRecordResponse> registerMonthlySelection(@Valid @RequestBody MonthlySelectionRequest request) {
        return portfolioSelectionUseCase.registerMonthlyDelinquency(request.getYear(), request.getMonth()).stream()
                .map(webMapper::toResponse)
                .toList();
    }

    @GetMapping("/status/{status}")
    public List<DelinquencyRecordResponse> listByStatus(@PathVariable String status) {
        return delinquencyManagementUseCase.findByStatus(status).stream()
                .map(webMapper::toResponse)
                .toList();
    }
}
