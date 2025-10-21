package com.cartera.cobranza.infrastructure.adapter.web.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class CollectionActionLogResponse {

    UUID id;
    UUID assignmentId;
    LocalDateTime actionDate;
    String actionType;
    String contactChannel;
    String comments;
    String createdBy;
    boolean paymentReceived;
    BigDecimal paymentAmount;
    LocalDate paymentDate;
}
