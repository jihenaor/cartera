package com.cartera.cobranza.infrastructure.adapter.as400;

import com.cartera.cobranza.domain.model.Company;
import com.cartera.cobranza.domain.model.DelinquencyRecord;
import com.cartera.cobranza.domain.port.out.ExternalDelinquencySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class As400DelinquencySourceAdapter implements ExternalDelinquencySource {

    @Override
    public List<DelinquencyRecord> fetchDelinquentCompaniesForPeriod(int year, int month) {
        // TODO: Integrate with AS400/DB2 data extraction. For now returns empty list as placeholder.
        return Collections.emptyList();
    }

    public List<DelinquencyRecord> sampleData(int year, int month) {
        Company company = Company.builder()
                .id(UUID.randomUUID())
                .nit("900123456")
                .name("Empresa Ejemplo S.A.")
                .email("cartera@ejemplo.com")
                .phone("6011234567")
                .address("Calle 123 #45-67")
                .active(true)
                .build();
        DelinquencyRecord record = DelinquencyRecord.builder()
                .company(company)
                .period(YearMonth.of(year, month))
                .outstandingAmount(new BigDecimal("1500000"))
                .dueDate(LocalDate.of(year, month, 25))
                .sourceSystem("AS400")
                .build();
        return List.of(record);
    }
}
