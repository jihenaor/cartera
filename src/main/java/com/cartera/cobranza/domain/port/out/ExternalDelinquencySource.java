package com.cartera.cobranza.domain.port.out;

import com.cartera.cobranza.domain.model.DelinquencyRecord;

import java.util.List;

public interface ExternalDelinquencySource {

    List<DelinquencyRecord> fetchDelinquentCompaniesForPeriod(int year, int month);
}
