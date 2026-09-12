package com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input;

import java.util.List;

public interface FindUseCase<ENTITY, ID> {
    ENTITY getById(ID id);
    List<ENTITY> getAll();
}
