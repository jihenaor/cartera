package com.cartera.cobranza.infrastructure.adapter.web.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CollectionManagerResponse {

    UUID id;
    String code;
    String fullName;
    String email;
    String phone;
    boolean active;
}
