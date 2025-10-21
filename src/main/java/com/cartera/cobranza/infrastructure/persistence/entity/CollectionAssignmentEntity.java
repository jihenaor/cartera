package com.cartera.cobranza.infrastructure.persistence.entity;

import com.cartera.cobranza.domain.model.AssignmentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "CO_COLLECTION_ASSIGNMENT")
@Getter
@Setter
public class CollectionAssignmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "DELINQUENCY_ID")
    private DelinquencyRecordEntity delinquencyRecord;

    @ManyToOne(optional = false)
    @JoinColumn(name = "MANAGER_ID")
    private CollectionManagerEntity manager;

    @Column(name = "ASSIGNED_DATE", nullable = false)
    private LocalDate assignedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 30)
    private AssignmentStatus status;

    @Column(name = "OBSERVATIONS", length = 500)
    private String observations;
}
