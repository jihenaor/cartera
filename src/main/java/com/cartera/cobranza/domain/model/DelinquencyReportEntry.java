package com.cartera.cobranza.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DelinquencyReportEntry {

    private UUID id;
    private Company company;
    private YearMonth reportPeriod;
    private String reportConsecutive;
    private String contributorType;
    private String contributorClass;
    private String economicActivityCode;
    private String departmentCode;
    private String municipalityCode;
    private String address;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private Integer employeesReported;
    private LocalDate dueDate;
    private Integer daysInArrears;
    private BigDecimal principalAmount;
    private BigDecimal interestAmount;
    private BigDecimal totalAmount;
    private LocalDate lastPaymentDate;
    private BigDecimal lastPaymentAmount;
    private String collectionStage;
    private String collectionChannel;
    private String managementResult;
    private String observation;
    private String agreementNumber;
    private LocalDate agreementDate;
    private String agreementStatus;
    private String enforcementProcessNumber;
    private LocalDate enforcementProcessDate;
    private String riskLevel;
    private String sourceFileName;
    private LocalDateTime generatedAt;
    private LocalDateTime updatedAt;
}
