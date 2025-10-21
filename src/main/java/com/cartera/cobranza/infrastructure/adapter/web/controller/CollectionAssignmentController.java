package com.cartera.cobranza.infrastructure.adapter.web.controller;

import com.cartera.cobranza.domain.model.CollectionAssignment;
import com.cartera.cobranza.domain.port.in.CollectionAssignmentUseCase;
import com.cartera.cobranza.domain.port.in.CollectionLogUseCase;
import com.cartera.cobranza.infrastructure.adapter.web.dto.AssignmentStatusUpdateRequest;
import com.cartera.cobranza.infrastructure.adapter.web.dto.CollectionActionLogRequest;
import com.cartera.cobranza.infrastructure.adapter.web.dto.CollectionActionLogResponse;
import com.cartera.cobranza.infrastructure.adapter.web.dto.CollectionAssignmentRequest;
import com.cartera.cobranza.infrastructure.adapter.web.dto.CollectionAssignmentResponse;
import com.cartera.cobranza.infrastructure.adapter.web.mapper.WebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class CollectionAssignmentController {

    private final CollectionAssignmentUseCase collectionAssignmentUseCase;
    private final CollectionLogUseCase collectionLogUseCase;
    private final WebMapper webMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CollectionAssignmentResponse assign(@Valid @RequestBody CollectionAssignmentRequest request) {
        CollectionAssignment assignment = collectionAssignmentUseCase.assignDelinquencyToManager(
                request.getDelinquencyId(),
                request.getManagerId(),
                request.getObservations()
        );
        return webMapper.toResponse(assignment);
    }

    @PutMapping("/{assignmentId}/status")
    public CollectionAssignmentResponse updateStatus(@PathVariable UUID assignmentId,
                                                      @Valid @RequestBody AssignmentStatusUpdateRequest request) {
        CollectionAssignment assignment = collectionAssignmentUseCase.updateAssignmentStatus(assignmentId, request.getStatus());
        return webMapper.toResponse(assignment);
    }

    @GetMapping("/manager/{managerId}")
    public List<CollectionAssignmentResponse> listAssignmentsForManager(@PathVariable UUID managerId,
                                                                        @RequestParam String status) {
        return collectionAssignmentUseCase.getAssignmentsForManager(managerId, status).stream()
                .map(webMapper::toResponse)
                .toList();
    }

    @PostMapping("/{assignmentId}/logs")
    @ResponseStatus(HttpStatus.CREATED)
    public CollectionActionLogResponse registerAction(@PathVariable UUID assignmentId,
                                                       @Valid @RequestBody CollectionActionLogRequest request) {
        CollectionAssignment assignment = collectionAssignmentUseCase.getAssignment(assignmentId);
        request.setAssignmentId(assignmentId);
        var log = webMapper.toDomain(request, assignment);
        var saved = collectionLogUseCase.registerAction(log);
        return webMapper.toResponse(saved);
    }

    @GetMapping("/{assignmentId}/logs")
    public List<CollectionActionLogResponse> listLogs(@PathVariable UUID assignmentId) {
        return collectionLogUseCase.getLogsByAssignment(assignmentId).stream()
                .map(webMapper::toResponse)
                .toList();
    }
}
