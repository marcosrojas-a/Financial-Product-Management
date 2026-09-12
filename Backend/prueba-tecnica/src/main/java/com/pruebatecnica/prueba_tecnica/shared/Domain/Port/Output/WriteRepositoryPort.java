package com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Output;

public interface WriteRepositoryPort <ENTITY> {
    ENTITY save(ENTITY entity);
}
