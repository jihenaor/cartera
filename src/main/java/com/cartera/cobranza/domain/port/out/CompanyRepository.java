package com.cartera.cobranza.domain.port.out;

import com.cartera.cobranza.domain.model.Company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository {

    Company save(Company company);

    Optional<Company> findById(UUID id);

    Optional<Company> findByNit(String nit);

    List<Company> findAllActive();
}
