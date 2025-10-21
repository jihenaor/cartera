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
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CollectionActionLog {

    private UUID id;
    private CollectionAssignment assignment;
    private LocalDateTime actionDate;
    private String actionType;
    private String contactChannel;
    private String comments;
    private String createdBy;
    private boolean paymentReceived;
    private BigDecimal paymentAmount;
    private LocalDate paymentDate;
}
