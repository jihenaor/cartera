package com.cartera.cobranza.infrastructure.adapter.web.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CompanyResponse {

    UUID id;
    String nit;
    String name;
    String email;
    String phone;
    String address;
    boolean active;
}
