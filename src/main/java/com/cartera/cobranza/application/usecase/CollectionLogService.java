package com.cartera.cobranza.application.usecase;

import com.cartera.cobranza.domain.model.CollectionActionLog;
import com.cartera.cobranza.domain.model.CollectionAssignment;
import com.cartera.cobranza.domain.model.DelinquencyRecord;
import com.cartera.cobranza.domain.model.DelinquencyStatus;
import com.cartera.cobranza.domain.port.in.CollectionLogUseCase;
import com.cartera.cobranza.domain.port.out.CollectionActionLogRepository;
import com.cartera.cobranza.domain.port.out.CollectionAssignmentRepository;
import com.cartera.cobranza.domain.port.out.DelinquencyRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CollectionLogService implements CollectionLogUseCase {

    private final CollectionActionLogRepository actionLogRepository;
    private final CollectionAssignmentRepository assignmentRepository;
    private final DelinquencyRecordRepository delinquencyRecordRepository;

    @Override
    public CollectionActionLog registerAction(CollectionActionLog log) {
        UUID assignmentId = log.getAssignment().getId();
        CollectionAssignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("Asignacion no encontrada: " + assignmentId));
        log.setAssignment(assignment);
        if (log.getActionDate() == null) {
            log.setActionDate(LocalDateTime.now());
        }
        if (log.isPaymentReceived()) {
            DelinquencyRecord record = assignment.getDelinquencyRecord();
            record.setStatus(DelinquencyStatus.PAID);
            record.setResolvedAt(LocalDateTime.now());
            delinquencyRecordRepository.save(record);
        }
        return actionLogRepository.save(log);
    }

    @Override
    public List<CollectionActionLog> getLogsByAssignment(UUID assignmentId) {
        return actionLogRepository.findByAssignment(assignmentId);
    }
}
