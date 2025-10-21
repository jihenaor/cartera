package com.cartera.cobranza.application.usecase;

import com.cartera.cobranza.domain.model.CollectionManager;
import com.cartera.cobranza.domain.port.in.CollectionManagerUseCase;
import com.cartera.cobranza.domain.port.out.CollectionManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CollectionManagerService implements CollectionManagerUseCase {

    private final CollectionManagerRepository managerRepository;

    @Override
    public CollectionManager registerOrUpdateManager(CollectionManager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public CollectionManager getManager(UUID id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gestor no encontrado: " + id));
    }

    @Override
    public List<CollectionManager> listActiveManagers() {
        return managerRepository.findAllActive();
    }
}
