package com.cartera.cobranza.infrastructure.adapter.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.UUID;

@Getter
@Setter
public class DelinquencyReportRequest {

    @NotNull
    private UUID companyId;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM")
    private YearMonth reportPeriod;

    @Size(max = 40)
    private String reportConsecutive;

    @Size(max = 40)
    private String contributorType;

    @Size(max = 40)
    private String contributorClass;

    @Size(max = 10)
    private String economicActivityCode;

    @Size(max = 5)
    private String departmentCode;

    @Size(max = 5)
    private String municipalityCode;

    @Size(max = 255)
    private String address;

    @Size(max = 120)
    private String contactName;

    @Size(max = 120)
    private String contactEmail;

    @Size(max = 40)
    private String contactPhone;

    private Integer employeesReported;

    private LocalDate dueDate;

    private Integer daysInArrears;

    private BigDecimal principalAmount;

    private BigDecimal interestAmount;

    private BigDecimal totalAmount;

    private LocalDate lastPaymentDate;

    private BigDecimal lastPaymentAmount;

    @Size(max = 40)
    private String collectionStage;

    @Size(max = 40)
    private String collectionChannel;

    @Size(max = 200)
    private String managementResult;

    @Size(max = 500)
    private String observation;

    @Size(max = 60)
    private String agreementNumber;

    private LocalDate agreementDate;

    @Size(max = 40)
    private String agreementStatus;

    @Size(max = 60)
    private String enforcementProcessNumber;

    private LocalDate enforcementProcessDate;

    @Size(max = 40)
    private String riskLevel;

    @Size(max = 120)
    private String sourceFileName;

    private LocalDateTime generatedAt;

    private LocalDateTime updatedAt;
}
