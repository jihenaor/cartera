package com.cartera.cobranza.infrastructure.persistence.entity;

import com.cartera.cobranza.domain.model.DelinquencyStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "CO_DELINQUENCY_RECORD")
@Getter
@Setter
public class DelinquencyRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "COMPANY_ID")
    private CompanyEntity company;

    @Column(name = "PERIOD_YEAR", nullable = false)
    private int periodYear;

    @Column(name = "PERIOD_MONTH", nullable = false)
    private int periodMonth;

    @Column(name = "OUTSTANDING_AMOUNT", nullable = false, precision = 15, scale = 2)
    private BigDecimal outstandingAmount;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 30)
    private DelinquencyStatus status;

    @Column(name = "REGISTERED_AT", nullable = false)
    private LocalDateTime registeredAt;

    @Column(name = "RESOLVED_AT")
    private LocalDateTime resolvedAt;

    @Column(name = "SOURCE_SYSTEM", length = 60)
    private String sourceSystem;
}
