package com.cartera.cobranza.domain.port.in;

import com.cartera.cobranza.domain.model.CollectionManager;

import java.util.List;
import java.util.UUID;

public interface CollectionManagerUseCase {

    CollectionManager registerOrUpdateManager(CollectionManager manager);

    CollectionManager getManager(UUID id);

    List<CollectionManager> listActiveManagers();
}
