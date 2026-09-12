package com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input;

public interface UpdateUseCase<COMMAND, ENTITY, ID> {
    ENTITY execute(ID id, COMMAND command);
}
