package com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Movimient;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Movement;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.FindUseCase;

import java.util.List;

public interface FindMovementPort extends FindUseCase<Movement, Long> {
    List<Movement> getByProductId(Long productId);
}
