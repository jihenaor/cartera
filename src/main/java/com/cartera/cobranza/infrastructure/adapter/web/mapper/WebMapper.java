package com.cartera.cobranza.infrastructure.adapter.web.mapper;

import com.cartera.cobranza.domain.model.CollectionActionLog;
import com.cartera.cobranza.domain.model.CollectionAssignment;
import com.cartera.cobranza.domain.model.CollectionManager;
import com.cartera.cobranza.domain.model.Company;
import com.cartera.cobranza.domain.model.DelinquencyRecord;
import com.cartera.cobranza.domain.model.DelinquencyReportEntry;
import com.cartera.cobranza.domain.model.DelinquencyStatus;
import com.cartera.cobranza.infrastructure.adapter.web.dto.CollectionActionLogRequest;
import com.cartera.cobranza.infrastructure.adapter.web.dto.CollectionActionLogResponse;
import com.cartera.cobranza.infrastructure.adapter.web.dto.CollectionAssignmentResponse;
import com.cartera.cobranza.infrastructure.adapter.web.dto.CollectionManagerRequest;
import com.cartera.cobranza.infrastructure.adapter.web.dto.CollectionManagerResponse;
import com.cartera.cobranza.infrastructure.adapter.web.dto.CompanyRequest;
import com.cartera.cobranza.infrastructure.adapter.web.dto.CompanyResponse;
import com.cartera.cobranza.infrastructure.adapter.web.dto.DelinquencyRecordRequest;
import com.cartera.cobranza.infrastructure.adapter.web.dto.DelinquencyRecordResponse;
import com.cartera.cobranza.infrastructure.adapter.web.dto.DelinquencyReportRequest;
import com.cartera.cobranza.infrastructure.adapter.web.dto.DelinquencyReportResponse;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component
public class WebMapper {

    public Company toDomain(CompanyRequest request) {
        return Company.builder()
                .nit(request.getNit())
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .active(request.isActive())
                .build();
    }

    public CompanyResponse toResponse(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .nit(company.getNit())
                .name(company.getName())
                .email(company.getEmail())
                .phone(company.getPhone())
                .address(company.getAddress())
                .active(company.isActive())
                .build();
    }

    public CollectionManager toDomain(CollectionManagerRequest request) {
        return CollectionManager.builder()
                .code(request.getCode())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .active(request.isActive())
                .build();
    }

    public CollectionManagerResponse toResponse(CollectionManager manager) {
        return CollectionManagerResponse.builder()
                .id(manager.getId())
                .code(manager.getCode())
                .fullName(manager.getFullName())
                .email(manager.getEmail())
                .phone(manager.getPhone())
                .active(manager.isActive())
                .build();
    }

    public DelinquencyRecord toDomain(DelinquencyRecordRequest request, Company company) {
        YearMonth period = YearMonth.parse(request.getPeriod());
        return DelinquencyRecord.builder()
                .company(company)
                .period(period)
                .outstandingAmount(request.getOutstandingAmount())
                .dueDate(request.getDueDate())
                .sourceSystem(request.getSourceSystem())
                .status(DelinquencyStatus.OPEN)
                .build();
    }

    public DelinquencyRecordResponse toResponse(DelinquencyRecord record) {
        return DelinquencyRecordResponse.builder()
                .id(record.getId())
                .companyId(record.getCompany().getId())
                .companyName(record.getCompany().getName())
                .period(record.getPeriod())
                .outstandingAmount(record.getOutstandingAmount())
                .dueDate(record.getDueDate())
                .status(record.getStatus().name())
                .registeredAt(record.getRegisteredAt())
                .resolvedAt(record.getResolvedAt())
                .sourceSystem(record.getSourceSystem())
                .build();
    }

    public CollectionActionLog toDomain(CollectionActionLogRequest request, CollectionAssignment assignment) {
        return CollectionActionLog.builder()
                .assignment(assignment)
                .actionDate(request.getActionDate())
                .actionType(request.getActionType())
                .contactChannel(request.getContactChannel())
                .comments(request.getComments())
                .createdBy(request.getCreatedBy())
                .paymentReceived(request.isPaymentReceived())
                .paymentAmount(request.getPaymentAmount())
                .paymentDate(request.getPaymentDate())
                .build();
    }

    public CollectionActionLogResponse toResponse(CollectionActionLog log) {
        return CollectionActionLogResponse.builder()
                .id(log.getId())
                .assignmentId(log.getAssignment().getId())
                .actionDate(log.getActionDate())
                .actionType(log.getActionType())
                .contactChannel(log.getContactChannel())
                .comments(log.getComments())
                .createdBy(log.getCreatedBy())
                .paymentReceived(log.isPaymentReceived())
                .paymentAmount(log.getPaymentAmount())
                .paymentDate(log.getPaymentDate())
                .build();
    }

    public CollectionAssignmentResponse toResponse(CollectionAssignment assignment) {
        return CollectionAssignmentResponse.builder()
                .id(assignment.getId())
                .delinquencyId(assignment.getDelinquencyRecord().getId())
                .managerId(assignment.getManager().getId())
                .assignedDate(assignment.getAssignedDate())
                .status(assignment.getStatus().name())
                .observations(assignment.getObservations())
                .build();
    }

    public DelinquencyReportEntry toDomain(DelinquencyReportRequest request, Company company) {
        return DelinquencyReportEntry.builder()
                .company(company)
                .reportPeriod(request.getReportPeriod())
                .reportConsecutive(request.getReportConsecutive())
                .contributorType(request.getContributorType())
                .contributorClass(request.getContributorClass())
                .economicActivityCode(request.getEconomicActivityCode())
                .departmentCode(request.getDepartmentCode())
                .municipalityCode(request.getMunicipalityCode())
                .address(request.getAddress())
                .contactName(request.getContactName())
                .contactEmail(request.getContactEmail())
                .contactPhone(request.getContactPhone())
                .employeesReported(request.getEmployeesReported())
                .dueDate(request.getDueDate())
                .daysInArrears(request.getDaysInArrears())
                .principalAmount(request.getPrincipalAmount())
                .interestAmount(request.getInterestAmount())
                .totalAmount(request.getTotalAmount())
                .lastPaymentDate(request.getLastPaymentDate())
                .lastPaymentAmount(request.getLastPaymentAmount())
                .collectionStage(request.getCollectionStage())
                .collectionChannel(request.getCollectionChannel())
                .managementResult(request.getManagementResult())
                .observation(request.getObservation())
                .agreementNumber(request.getAgreementNumber())
                .agreementDate(request.getAgreementDate())
                .agreementStatus(request.getAgreementStatus())
                .enforcementProcessNumber(request.getEnforcementProcessNumber())
                .enforcementProcessDate(request.getEnforcementProcessDate())
                .riskLevel(request.getRiskLevel())
                .sourceFileName(request.getSourceFileName())
                .generatedAt(request.getGeneratedAt())
                .updatedAt(request.getUpdatedAt())
                .build();
    }

    public DelinquencyReportResponse toResponse(DelinquencyReportEntry entry) {
        return DelinquencyReportResponse.builder()
                .id(entry.getId())
                .companyId(entry.getCompany().getId())
                .reportPeriod(entry.getReportPeriod())
                .reportConsecutive(entry.getReportConsecutive())
                .contributorType(entry.getContributorType())
                .contributorClass(entry.getContributorClass())
                .economicActivityCode(entry.getEconomicActivityCode())
                .departmentCode(entry.getDepartmentCode())
                .municipalityCode(entry.getMunicipalityCode())
                .address(entry.getAddress())
                .contactName(entry.getContactName())
                .contactEmail(entry.getContactEmail())
                .contactPhone(entry.getContactPhone())
                .employeesReported(entry.getEmployeesReported())
                .dueDate(entry.getDueDate())
                .daysInArrears(entry.getDaysInArrears())
                .principalAmount(entry.getPrincipalAmount())
                .interestAmount(entry.getInterestAmount())
                .totalAmount(entry.getTotalAmount())
                .lastPaymentDate(entry.getLastPaymentDate())
                .lastPaymentAmount(entry.getLastPaymentAmount())
                .collectionStage(entry.getCollectionStage())
                .collectionChannel(entry.getCollectionChannel())
                .managementResult(entry.getManagementResult())
                .observation(entry.getObservation())
                .agreementNumber(entry.getAgreementNumber())
                .agreementDate(entry.getAgreementDate())
                .agreementStatus(entry.getAgreementStatus())
                .enforcementProcessNumber(entry.getEnforcementProcessNumber())
                .enforcementProcessDate(entry.getEnforcementProcessDate())
                .riskLevel(entry.getRiskLevel())
                .sourceFileName(entry.getSourceFileName())
                .generatedAt(entry.getGeneratedAt())
                .updatedAt(entry.getUpdatedAt())
                .build();
    }
}
