package com.cartera.cobranza.infrastructure.persistence.mapper;

import com.cartera.cobranza.domain.model.DelinquencyRecord;
import com.cartera.cobranza.infrastructure.persistence.entity.DelinquencyRecordEntity;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component
public class DelinquencyRecordEntityMapper {

    private final CompanyEntityMapper companyEntityMapper;

    public DelinquencyRecordEntityMapper(CompanyEntityMapper companyEntityMapper) {
        this.companyEntityMapper = companyEntityMapper;
    }

    public DelinquencyRecordEntity toEntity(DelinquencyRecord record) {
        if (record == null) {
            return null;
        }
        DelinquencyRecordEntity entity = new DelinquencyRecordEntity();
        entity.setId(record.getId());
        entity.setCompany(companyEntityMapper.toEntity(record.getCompany()));
        YearMonth period = record.getPeriod();
        if (period != null) {
            entity.setPeriodYear(period.getYear());
            entity.setPeriodMonth(period.getMonthValue());
        }
        entity.setOutstandingAmount(record.getOutstandingAmount());
        entity.setDueDate(record.getDueDate());
        entity.setStatus(record.getStatus());
        entity.setRegisteredAt(record.getRegisteredAt());
        entity.setResolvedAt(record.getResolvedAt());
        entity.setSourceSystem(record.getSourceSystem());
        return entity;
    }

    public DelinquencyRecord toDomain(DelinquencyRecordEntity entity) {
        if (entity == null) {
            return null;
        }
        return DelinquencyRecord.builder()
                .id(entity.getId())
                .company(companyEntityMapper.toDomain(entity.getCompany()))
                .period(YearMonth.of(entity.getPeriodYear(), entity.getPeriodMonth()))
                .outstandingAmount(entity.getOutstandingAmount())
                .dueDate(entity.getDueDate())
                .status(entity.getStatus())
                .registeredAt(entity.getRegisteredAt())
                .resolvedAt(entity.getResolvedAt())
                .sourceSystem(entity.getSourceSystem())
                .build();
    }
}
