package com.cartera.cobranza.domain.port.out;

import com.cartera.cobranza.domain.model.DelinquencyReportEntry;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DelinquencyReportRepository {

    DelinquencyReportEntry save(DelinquencyReportEntry entry);

    Optional<DelinquencyReportEntry> findById(UUID id);

    List<DelinquencyReportEntry> findByReportPeriod(YearMonth period);

    List<DelinquencyReportEntry> findByCompany(UUID companyId);
}
