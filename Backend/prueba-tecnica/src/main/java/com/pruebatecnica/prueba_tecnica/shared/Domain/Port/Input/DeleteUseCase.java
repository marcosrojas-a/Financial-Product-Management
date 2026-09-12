package com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input;

public interface DeleteUseCase<ID> {
    void execute(ID id);
}
