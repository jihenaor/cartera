package com.cartera.cobranza.infrastructure.adapter.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AssignmentStatusUpdateRequest {

    @NotBlank
    private String status;
}
