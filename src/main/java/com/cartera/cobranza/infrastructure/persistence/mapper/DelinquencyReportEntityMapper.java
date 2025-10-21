package com.cartera.cobranza.infrastructure.persistence.mapper;

import com.cartera.cobranza.domain.model.DelinquencyReportEntry;
import com.cartera.cobranza.infrastructure.persistence.entity.DelinquencyReportEntity;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component
public class DelinquencyReportEntityMapper {

    private final CompanyEntityMapper companyEntityMapper;

    public DelinquencyReportEntityMapper(CompanyEntityMapper companyEntityMapper) {
        this.companyEntityMapper = companyEntityMapper;
    }

    public DelinquencyReportEntity toEntity(DelinquencyReportEntry entry) {
        DelinquencyReportEntity entity = new DelinquencyReportEntity();
        entity.setId(entry.getId());
        entity.setCompany(companyEntityMapper.toEntity(entry.getCompany()));
        YearMonth period = entry.getReportPeriod();
        if (period != null) {
            entity.setReportYear(period.getYear());
            entity.setReportMonth(period.getMonthValue());
        }
        entity.setReportConsecutive(entry.getReportConsecutive());
        entity.setContributorType(entry.getContributorType());
        entity.setContributorClass(entry.getContributorClass());
        entity.setEconomicActivityCode(entry.getEconomicActivityCode());
        entity.setDepartmentCode(entry.getDepartmentCode());
        entity.setMunicipalityCode(entry.getMunicipalityCode());
        entity.setAddress(entry.getAddress());
        entity.setContactName(entry.getContactName());
        entity.setContactEmail(entry.getContactEmail());
        entity.setContactPhone(entry.getContactPhone());
        entity.setEmployeesReported(entry.getEmployeesReported());
        entity.setDueDate(entry.getDueDate());
        entity.setDaysInArrears(entry.getDaysInArrears());
        entity.setPrincipalAmount(entry.getPrincipalAmount());
        entity.setInterestAmount(entry.getInterestAmount());
        entity.setTotalAmount(entry.getTotalAmount());
        entity.setLastPaymentDate(entry.getLastPaymentDate());
        entity.setLastPaymentAmount(entry.getLastPaymentAmount());
        entity.setCollectionStage(entry.getCollectionStage());
        entity.setCollectionChannel(entry.getCollectionChannel());
        entity.setManagementResult(entry.getManagementResult());
        entity.setObservation(entry.getObservation());
        entity.setAgreementNumber(entry.getAgreementNumber());
        entity.setAgreementDate(entry.getAgreementDate());
        entity.setAgreementStatus(entry.getAgreementStatus());
        entity.setEnforcementProcessNumber(entry.getEnforcementProcessNumber());
        entity.setEnforcementProcessDate(entry.getEnforcementProcessDate());
        entity.setRiskLevel(entry.getRiskLevel());
        entity.setSourceFileName(entry.getSourceFileName());
        entity.setGeneratedAt(entry.getGeneratedAt());
        entity.setUpdatedAt(entry.getUpdatedAt());
        return entity;
    }

    public DelinquencyReportEntry toDomain(DelinquencyReportEntity entity) {
        YearMonth period = YearMonth.of(entity.getReportYear(), entity.getReportMonth());
        return DelinquencyReportEntry.builder()
                .id(entity.getId())
                .company(companyEntityMapper.toDomain(entity.getCompany()))
                .reportPeriod(period)
                .reportConsecutive(entity.getReportConsecutive())
                .contributorType(entity.getContributorType())
                .contributorClass(entity.getContributorClass())
                .economicActivityCode(entity.getEconomicActivityCode())
                .departmentCode(entity.getDepartmentCode())
                .municipalityCode(entity.getMunicipalityCode())
                .address(entity.getAddress())
                .contactName(entity.getContactName())
                .contactEmail(entity.getContactEmail())
                .contactPhone(entity.getContactPhone())
                .employeesReported(entity.getEmployeesReported())
                .dueDate(entity.getDueDate())
                .daysInArrears(entity.getDaysInArrears())
                .principalAmount(entity.getPrincipalAmount())
                .interestAmount(entity.getInterestAmount())
                .totalAmount(entity.getTotalAmount())
                .lastPaymentDate(entity.getLastPaymentDate())
                .lastPaymentAmount(entity.getLastPaymentAmount())
                .collectionStage(entity.getCollectionStage())
                .collectionChannel(entity.getCollectionChannel())
                .managementResult(entity.getManagementResult())
                .observation(entity.getObservation())
                .agreementNumber(entity.getAgreementNumber())
                .agreementDate(entity.getAgreementDate())
                .agreementStatus(entity.getAgreementStatus())
                .enforcementProcessNumber(entity.getEnforcementProcessNumber())
                .enforcementProcessDate(entity.getEnforcementProcessDate())
                .riskLevel(entity.getRiskLevel())
                .sourceFileName(entity.getSourceFileName())
                .generatedAt(entity.getGeneratedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
