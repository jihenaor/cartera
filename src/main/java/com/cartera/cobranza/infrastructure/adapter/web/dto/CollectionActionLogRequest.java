package com.cartera.cobranza.infrastructure.adapter.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CollectionActionLogRequest {

    private UUID assignmentId;

    @NotBlank
    private String actionType;

    @NotBlank
    private String contactChannel;

    private String comments;

    @NotBlank
    private String createdBy;

    private boolean paymentReceived;

    private BigDecimal paymentAmount;

    private LocalDate paymentDate;

    private LocalDateTime actionDate;
}
