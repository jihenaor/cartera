package com.cartera.cobranza.infrastructure.adapter.web.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.UUID;

@Value
@Builder
public class DelinquencyRecordResponse {

    UUID id;
    UUID companyId;
    String companyName;
    YearMonth period;
    BigDecimal outstandingAmount;
    LocalDate dueDate;
    String status;
    LocalDateTime registeredAt;
    LocalDateTime resolvedAt;
    String sourceSystem;
}
