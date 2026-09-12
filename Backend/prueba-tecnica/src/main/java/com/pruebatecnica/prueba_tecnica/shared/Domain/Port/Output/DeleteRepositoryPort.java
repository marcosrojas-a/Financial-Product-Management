package com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Output;

public interface DeleteRepositoryPort <ID> {
    void deleteById(ID id);
}
