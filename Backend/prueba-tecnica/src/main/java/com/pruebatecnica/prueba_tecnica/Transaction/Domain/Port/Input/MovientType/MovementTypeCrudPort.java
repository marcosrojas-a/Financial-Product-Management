package com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.MovientType;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.MovementType;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.CrudUseCase;

public interface MovementTypeCrudPort extends CrudUseCase <CreateMovementTypeCommand,UpdateMovementTypeCommand, MovementType,Long> {
}
