package com.cartera.cobranza.domain.port.out;

import com.cartera.cobranza.domain.model.CollectionAssignment;
import com.cartera.cobranza.domain.model.AssignmentStatus;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CollectionAssignmentRepository {

    CollectionAssignment save(CollectionAssignment assignment);

    Optional<CollectionAssignment> findById(UUID id);

    List<CollectionAssignment> findByManagerAndStatus(UUID managerId, AssignmentStatus status);

    List<CollectionAssignment> findByPeriod(YearMonth period);
}
