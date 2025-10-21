package com.cartera.cobranza.infrastructure.adapter.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompanyRequest {

    @NotBlank
    @Size(max = 30)
    private String nit;

    @NotBlank
    @Size(max = 150)
    private String name;

    @Email
    private String email;

    @Size(max = 30)
    private String phone;

    @Size(max = 255)
    private String address;

    private boolean active = true;
}
