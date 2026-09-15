package com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input;

import java.util.List;

public interface CrudUseCase <CREATE_CMD, UPDATE_CMD, ENTITY, ID> {
    ENTITY create(CREATE_CMD command);
    ENTITY update(ID id, UPDATE_CMD command);
    void delete(ID id);
    ENTITY getById(ID id);
    List<ENTITY> getAll();
}
