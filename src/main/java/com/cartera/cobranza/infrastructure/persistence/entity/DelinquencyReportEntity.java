package com.cartera.cobranza.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CO_DELINQUENCY_REPORT")
@Getter
@Setter
public class DelinquencyReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "COMPANY_ID")
    private CompanyEntity company;

    @Column(name = "REPORT_YEAR", nullable = false)
    private int reportYear;

    @Column(name = "REPORT_MONTH", nullable = false)
    private int reportMonth;

    @Column(name = "REPORT_CONSECUTIVE", length = 40)
    private String reportConsecutive;

    @Column(name = "CONTRIBUTOR_TYPE", length = 40)
    private String contributorType;

    @Column(name = "CONTRIBUTOR_CLASS", length = 40)
    private String contributorClass;

    @Column(name = "ECONOMIC_ACTIVITY_CODE", length = 10)
    private String economicActivityCode;

    @Column(name = "DEPARTMENT_CODE", length = 5)
    private String departmentCode;

    @Column(name = "MUNICIPALITY_CODE", length = 5)
    private String municipalityCode;

    @Column(name = "ADDRESS", length = 255)
    private String address;

    @Column(name = "CONTACT_NAME", length = 120)
    private String contactName;

    @Column(name = "CONTACT_EMAIL", length = 120)
    private String contactEmail;

    @Column(name = "CONTACT_PHONE", length = 40)
    private String contactPhone;

    @Column(name = "EMPLOYEES_REPORTED")
    private Integer employeesReported;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @Column(name = "DAYS_IN_ARREARS")
    private Integer daysInArrears;

    @Column(name = "PRINCIPAL_AMOUNT", precision = 15, scale = 2)
    private BigDecimal principalAmount;

    @Column(name = "INTEREST_AMOUNT", precision = 15, scale = 2)
    private BigDecimal interestAmount;

    @Column(name = "TOTAL_AMOUNT", precision = 15, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "LAST_PAYMENT_DATE")
    private LocalDate lastPaymentDate;

    @Column(name = "LAST_PAYMENT_AMOUNT", precision = 15, scale = 2)
    private BigDecimal lastPaymentAmount;

    @Column(name = "COLLECTION_STAGE", length = 40)
    private String collectionStage;

    @Column(name = "COLLECTION_CHANNEL", length = 40)
    private String collectionChannel;

    @Column(name = "MANAGEMENT_RESULT", length = 200)
    private String managementResult;

    @Column(name = "OBSERVATION", length = 500)
    private String observation;

    @Column(name = "AGREEMENT_NUMBER", length = 60)
    private String agreementNumber;

    @Column(name = "AGREEMENT_DATE")
    private LocalDate agreementDate;

    @Column(name = "AGREEMENT_STATUS", length = 40)
    private String agreementStatus;

    @Column(name = "ENFORCEMENT_PROCESS_NUMBER", length = 60)
    private String enforcementProcessNumber;

    @Column(name = "ENFORCEMENT_PROCESS_DATE")
    private LocalDate enforcementProcessDate;

    @Column(name = "RISK_LEVEL", length = 40)
    private String riskLevel;

    @Column(name = "SOURCE_FILE_NAME", length = 120)
    private String sourceFileName;

    @Column(name = "GENERATED_AT")
    private LocalDateTime generatedAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}
