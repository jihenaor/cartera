package com.cartera.cobranza.domain.port.in;

import com.cartera.cobranza.domain.model.DelinquencyRecord;

import java.util.List;

public interface DelinquencyManagementUseCase {

    DelinquencyRecord createManualRecord(DelinquencyRecord record);

    List<DelinquencyRecord> findByStatus(String status);
}
