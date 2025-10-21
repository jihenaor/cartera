package com.cartera.cobranza.domain.port.in;

import com.cartera.cobranza.domain.model.DelinquencyRecord;

import java.util.List;

public interface PortfolioSelectionUseCase {

    List<DelinquencyRecord> registerMonthlyDelinquency(int year, int month);
}
