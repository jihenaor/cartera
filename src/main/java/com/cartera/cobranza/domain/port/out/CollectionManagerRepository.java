package com.cartera.cobranza.domain.port.out;

import com.cartera.cobranza.domain.model.CollectionManager;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CollectionManagerRepository {

    CollectionManager save(CollectionManager manager);

    Optional<CollectionManager> findById(UUID id);

    Optional<CollectionManager> findByCode(String code);

    List<CollectionManager> findAllActive();
}
