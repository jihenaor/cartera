package com.cartera.cobranza.domain.port.in;

import com.cartera.cobranza.domain.model.CollectionActionLog;

import java.util.List;
import java.util.UUID;

public interface CollectionLogUseCase {

    CollectionActionLog registerAction(CollectionActionLog log);

    List<CollectionActionLog> getLogsByAssignment(UUID assignmentId);
}
