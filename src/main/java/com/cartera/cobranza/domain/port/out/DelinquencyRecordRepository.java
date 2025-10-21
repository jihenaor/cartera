package com.cartera.cobranza.domain.port.out;

import com.cartera.cobranza.domain.model.DelinquencyRecord;
import com.cartera.cobranza.domain.model.DelinquencyStatus;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DelinquencyRecordRepository {

    DelinquencyRecord save(DelinquencyRecord record);

    Optional<DelinquencyRecord> findById(UUID id);

    List<DelinquencyRecord> findByCompanyAndPeriod(UUID companyId, YearMonth period);

    List<DelinquencyRecord> findByStatus(DelinquencyStatus status);
}
