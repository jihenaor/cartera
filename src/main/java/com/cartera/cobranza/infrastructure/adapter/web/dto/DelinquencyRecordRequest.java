package com.cartera.cobranza.infrastructure.adapter.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class DelinquencyRecordRequest {

    @NotNull
    private UUID companyId;

    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}")
    private String period;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal outstandingAmount;

    private LocalDate dueDate;

    private String sourceSystem;
}
