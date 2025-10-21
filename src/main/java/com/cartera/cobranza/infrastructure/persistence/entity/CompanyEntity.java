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
@Table(name = "CO_COMPANY")
@Getter
@Setter
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "NIT", nullable = false, unique = true, length = 30)
    private String nit;

    @Column(name = "NAME", nullable = false, length = 150)
    private String name;

    @Column(name = "EMAIL", length = 120)
    private String email;

    @Column(name = "PHONE", length = 30)
    private String phone;

    @Column(name = "ADDRESS", length = 255)
    private String address;

    @Column(name = "ACTIVE", nullable = false)
    private boolean active = true;
}
