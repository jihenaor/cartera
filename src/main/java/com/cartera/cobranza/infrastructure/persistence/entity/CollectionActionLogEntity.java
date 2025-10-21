package com.cartera.cobranza.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CO_COLLECTION_ACTION_LOG")
@Getter
@Setter
public class CollectionActionLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ASSIGNMENT_ID")
    private CollectionAssignmentEntity assignment;

    @Column(name = "ACTION_DATE", nullable = false)
    private LocalDateTime actionDate;

    @Column(name = "ACTION_TYPE", length = 60)
    private String actionType;

    @Column(name = "CONTACT_CHANNEL", length = 60)
    private String contactChannel;

    @Column(name = "COMMENTS", length = 1000)
    private String comments;

    @Column(name = "CREATED_BY", length = 60)
    private String createdBy;

    @Column(name = "PAYMENT_RECEIVED")
    private boolean paymentReceived;

    @Column(name = "PAYMENT_AMOUNT", precision = 15, scale = 2)
    private BigDecimal paymentAmount;

    @Column(name = "PAYMENT_DATE")
    private LocalDate paymentDate;
}
