package com.cartera.cobranza.infrastructure.adapter.web.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class CollectionAssignmentResponse {

    UUID id;
    UUID delinquencyId;
    UUID managerId;
    LocalDate assignedDate;
    String status;
    String observations;
}
