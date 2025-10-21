package com.cartera.cobranza.infrastructure.adapter.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CollectionManagerRequest {

    @NotBlank
    @Size(max = 30)
    private String code;

    @NotBlank
    @Size(max = 120)
    private String fullName;

    @Email
    private String email;

    @Size(max = 30)
    private String phone;

    private boolean active = true;
}
