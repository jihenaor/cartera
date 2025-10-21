package com.cartera.cobranza.domain.port.in;

import com.cartera.cobranza.domain.model.Company;

import java.util.List;
import java.util.UUID;

public interface CompanyManagementUseCase {

    Company registerOrUpdateCompany(Company company);

    Company getCompany(UUID id);

    List<Company> listActiveCompanies();
}
