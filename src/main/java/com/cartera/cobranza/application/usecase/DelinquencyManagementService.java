package com.cartera.cobranza.application.usecase;

import com.cartera.cobranza.domain.model.DelinquencyRecord;
import com.cartera.cobranza.domain.model.DelinquencyStatus;
import com.cartera.cobranza.domain.port.in.DelinquencyManagementUseCase;
import com.cartera.cobranza.domain.port.out.DelinquencyRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DelinquencyManagementService implements DelinquencyManagementUseCase {

    private final DelinquencyRecordRepository delinquencyRecordRepository;

    @Override
    public DelinquencyRecord createManualRecord(DelinquencyRecord record) {
        record.setStatus(DelinquencyStatus.OPEN);
        record.setRegisteredAt(LocalDateTime.now());
        return delinquencyRecordRepository.save(record);
    }

    @Override
    public List<DelinquencyRecord> findByStatus(String status) {
        DelinquencyStatus delinquencyStatus = DelinquencyStatus.valueOf(status.toUpperCase());
        return delinquencyRecordRepository.findByStatus(delinquencyStatus);
    }
}
