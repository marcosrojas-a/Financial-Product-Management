package com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Output;

import java.util.List;
import java.util.Optional;

public interface ReadRepositoryPort<ENTITY, ID> {
    Optional<ENTITY> findById(ID id);
    List<ENTITY> findAll();
}
