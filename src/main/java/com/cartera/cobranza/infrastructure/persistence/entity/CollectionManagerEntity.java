package com.cartera.cobranza.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "CO_MANAGER")
@Getter
@Setter
public class CollectionManagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "CODE", nullable = false, unique = true, length = 30)
    private String code;

    @Column(name = "FULL_NAME", nullable = false, length = 120)
    private String fullName;

    @Column(name = "EMAIL", length = 120)
    private String email;

    @Column(name = "PHONE", length = 30)
    private String phone;

    @Column(name = "ACTIVE", nullable = false)
    private boolean active = true;
}
