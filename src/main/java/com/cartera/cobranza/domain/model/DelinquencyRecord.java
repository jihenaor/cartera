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
public class DelinquencyRecord {

    private UUID id;
    private Company company;
    private YearMonth period;
    private BigDecimal outstandingAmount;
    private LocalDate dueDate;
    private DelinquencyStatus status;
    private LocalDateTime registeredAt;
    private LocalDateTime resolvedAt;
    private String sourceSystem;
}
