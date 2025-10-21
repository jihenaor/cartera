package com.cartera.cobranza.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CollectionAssignment {

    private UUID id;
    private DelinquencyRecord delinquencyRecord;
    private CollectionManager manager;
    private LocalDate assignedDate;
    private AssignmentStatus status;
    private String observations;
}
