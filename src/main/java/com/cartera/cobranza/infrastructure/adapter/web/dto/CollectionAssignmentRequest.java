package com.cartera.cobranza.infrastructure.adapter.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CollectionAssignmentRequest {

    @NotNull
    private UUID delinquencyId;

    @NotNull
    private UUID managerId;

    @NotBlank
    private String observations;
}
