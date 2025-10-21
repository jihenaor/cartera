package com.cartera.cobranza.infrastructure.adapter.web.controller;

import com.cartera.cobranza.domain.model.CollectionManager;
import com.cartera.cobranza.domain.port.in.CollectionManagerUseCase;
import com.cartera.cobranza.infrastructure.adapter.web.dto.CollectionManagerRequest;
import com.cartera.cobranza.infrastructure.adapter.web.dto.CollectionManagerResponse;
import com.cartera.cobranza.infrastructure.adapter.web.mapper.WebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/managers")
@RequiredArgsConstructor
@Validated
public class CollectionManagerController {

    private final CollectionManagerUseCase collectionManagerUseCase;
    private final WebMapper webMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CollectionManagerResponse createManager(@Valid @RequestBody CollectionManagerRequest request) {
        CollectionManager manager = webMapper.toDomain(request);
        CollectionManager saved = collectionManagerUseCase.registerOrUpdateManager(manager);
        return webMapper.toResponse(saved);
    }

    @PutMapping("/{id}")
    public CollectionManagerResponse updateManager(@PathVariable UUID id, @Valid @RequestBody CollectionManagerRequest request) {
        CollectionManager manager = webMapper.toDomain(request);
        manager.setId(id);
        CollectionManager updated = collectionManagerUseCase.registerOrUpdateManager(manager);
        return webMapper.toResponse(updated);
    }

    @GetMapping
    public List<CollectionManagerResponse> listActiveManagers() {
        return collectionManagerUseCase.listActiveManagers().stream()
                .map(webMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public CollectionManagerResponse getManager(@PathVariable UUID id) {
        CollectionManager manager = collectionManagerUseCase.getManager(id);
        return webMapper.toResponse(manager);
    }
}
