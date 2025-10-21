package com.cartera.cobranza.domain.port.in;

import com.cartera.cobranza.domain.model.CollectionAssignment;

import java.util.List;
import java.util.UUID;

public interface CollectionAssignmentUseCase {

    CollectionAssignment assignDelinquencyToManager(UUID delinquencyId, UUID managerId, String observations);

    CollectionAssignment updateAssignmentStatus(UUID assignmentId, String status);

    List<CollectionAssignment> getAssignmentsForManager(UUID managerId, String status);

    CollectionAssignment getAssignment(UUID assignmentId);
}
