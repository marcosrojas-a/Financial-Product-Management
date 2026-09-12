package com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Output;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Movement;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Output.ReadRepositoryPort;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Output.WriteRepositoryPort;

import java.util.List;

public interface MovementRepositoryPort extends ReadRepositoryPort<Movement, Long>, WriteRepositoryPort<Movement> {
    List<Movement> findByProductId(Long productId);
}
