package com.cartera.cobranza.domain.port.out;

import com.cartera.cobranza.domain.model.CollectionActionLog;

import java.util.List;
import java.util.UUID;

public interface CollectionActionLogRepository {

    CollectionActionLog save(CollectionActionLog log);

    List<CollectionActionLog> findByAssignment(UUID assignmentId);
}
