package com.cartera.cobranza.application.usecase;

import com.cartera.cobranza.domain.model.AssignmentStatus;
import com.cartera.cobranza.domain.model.CollectionAssignment;
import com.cartera.cobranza.domain.model.CollectionManager;
import com.cartera.cobranza.domain.model.DelinquencyRecord;
import com.cartera.cobranza.domain.model.DelinquencyStatus;
import com.cartera.cobranza.domain.port.in.CollectionAssignmentUseCase;
import com.cartera.cobranza.domain.port.out.CollectionAssignmentRepository;
import com.cartera.cobranza.domain.port.out.CollectionManagerRepository;
import com.cartera.cobranza.domain.port.out.DelinquencyRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CollectionAssignmentService implements CollectionAssignmentUseCase {

    private final CollectionAssignmentRepository assignmentRepository;
    private final CollectionManagerRepository managerRepository;
    private final DelinquencyRecordRepository delinquencyRecordRepository;

    @Override
    public CollectionAssignment assignDelinquencyToManager(UUID delinquencyId, UUID managerId, String observations) {
        DelinquencyRecord record = delinquencyRecordRepository.findById(delinquencyId)
                .orElseThrow(() -> new IllegalArgumentException("Registro de cartera no encontrado: " + delinquencyId));
        CollectionManager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new IllegalArgumentException("Gestor no encontrado: " + managerId));

        CollectionAssignment assignment = CollectionAssignment.builder()
                .delinquencyRecord(record)
                .manager(manager)
                .assignedDate(LocalDate.now())
                .status(AssignmentStatus.ASSIGNED)
                .observations(observations)
                .build();

        record.setStatus(DelinquencyStatus.IN_COLLECTION);
        delinquencyRecordRepository.save(record);

        return assignmentRepository.save(assignment);
    }

    @Override
    public CollectionAssignment updateAssignmentStatus(UUID assignmentId, String status) {
        CollectionAssignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("Asignacion no encontrada: " + assignmentId));
        AssignmentStatus newStatus = AssignmentStatus.valueOf(status.toUpperCase(Locale.ROOT));
        assignment.setStatus(newStatus);
        if (AssignmentStatus.CLOSED == newStatus) {
            DelinquencyRecord record = assignment.getDelinquencyRecord();
            record.setStatus(DelinquencyStatus.CLOSED);
            record.setResolvedAt(java.time.LocalDateTime.now());
            delinquencyRecordRepository.save(record);
        }
        return assignmentRepository.save(assignment);
    }

    @Override
    public List<CollectionAssignment> getAssignmentsForManager(UUID managerId, String status) {
        AssignmentStatus assignmentStatus = AssignmentStatus.valueOf(status.toUpperCase(Locale.ROOT));
        return assignmentRepository.findByManagerAndStatus(managerId, assignmentStatus);
    }

    @Override
    public CollectionAssignment getAssignment(UUID assignmentId) {
        return assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("Asignacion no encontrada: " + assignmentId));
    }
}
