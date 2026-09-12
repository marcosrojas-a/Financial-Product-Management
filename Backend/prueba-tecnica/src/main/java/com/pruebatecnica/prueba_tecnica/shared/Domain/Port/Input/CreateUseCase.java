package com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input;

public interface CreateUseCase<COMMAND, ENTITY> {
    ENTITY execute(COMMAND command);
}
