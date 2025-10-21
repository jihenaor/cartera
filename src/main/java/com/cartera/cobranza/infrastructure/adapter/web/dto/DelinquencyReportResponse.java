package com.cartera.cobranza.infrastructure.adapter.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.UUID;

@Getter
@Builder
public class DelinquencyReportResponse {

    private final UUID id;
    private final UUID companyId;
    private final YearMonth reportPeriod;
    private final String reportConsecutive;
    private final String contributorType;
    private final String contributorClass;
    private final String economicActivityCode;
    private final String departmentCode;
    private final String municipalityCode;
    private final String address;
    private final String contactName;
    private final String contactEmail;
    private final String contactPhone;
    private final Integer employeesReported;
    private final LocalDate dueDate;
    private final Integer daysInArrears;
    private final BigDecimal principalAmount;
    private final BigDecimal interestAmount;
    private final BigDecimal totalAmount;
    private final LocalDate lastPaymentDate;
    private final BigDecimal lastPaymentAmount;
    private final String collectionStage;
    private final String collectionChannel;
    private final String managementResult;
    private final String observation;
    private final String agreementNumber;
    private final LocalDate agreementDate;
    private final String agreementStatus;
    private final String enforcementProcessNumber;
    private final LocalDate enforcementProcessDate;
    private final String riskLevel;
    private final String sourceFileName;
    private final LocalDateTime generatedAt;
    private final LocalDateTime updatedAt;
}
