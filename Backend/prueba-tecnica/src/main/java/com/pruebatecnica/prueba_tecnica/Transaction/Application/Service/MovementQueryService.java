package com.pruebatecnica.prueba_tecnica.Transaction.Application.Service;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Movement;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Movimient.FindMovementPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Output.MovementRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementQueryService implements FindMovementPort {

    private final MovementRepositoryPort movementRepositoryPort;

    public MovementQueryService(MovementRepositoryPort movementRepositoryPort) {
        this.movementRepositoryPort = movementRepositoryPort;
    }

    @Override
    public Movement getById(Long id) {
        return movementRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El movimiento con id " + id + " no existe."));
    }

    @Override
    public List<Movement> getAll() {
        return movementRepositoryPort.findAll();
    }

    @Override
    public List<Movement> getByProductId(Long productId) {
        return movementRepositoryPort.findByProductId(productId);
    }
}
