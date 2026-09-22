package com.pruebatecnica.prueba_tecnica.Transaction.Application.Service;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.MovementType;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.MovientTypeInputs.CreateMovementTypeCommand;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.MovientTypeInputs.MovementTypeCrudPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.MovientTypeInputs.UpdateMovementTypeCommand;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Output.MovementTypeRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementTypeService implements MovementTypeCrudPort {

    private final MovementTypeRepositoryPort movementTypeRepositoryPort;

    public MovementTypeService(MovementTypeRepositoryPort movementTypeRepositoryPort) {
        this.movementTypeRepositoryPort = movementTypeRepositoryPort;
    }

    @Override
    @Transactional
    public MovementType create(CreateMovementTypeCommand command) {
        // Reutiliza la validación que ya existe
        MovementType.validateAllowedCode(command.getCode());
        validateName(command.getName());

        if (movementTypeRepositoryPort.findByCode(command.getCode()).isPresent()) {
            throw new IllegalArgumentException(
                    "Ya existe un tipo de movimiento con el código " + command.getCode());
        }

        MovementType movementType = new MovementType(null, command.getCode(), command.getName(), command.isActive());
        return movementTypeRepositoryPort.save(movementType);
    }

    @Override
    @Transactional
    public MovementType update(Long id, UpdateMovementTypeCommand command) {
        MovementType existing = getMovementTypeOrThrow(id);

        MovementType.validateAllowedCode(command.getCode());
        validateName(command.getName());

        existing.setCode(command.getCode());
        existing.setName(command.getName());
        existing.setActive(command.isActive());

        return movementTypeRepositoryPort.save(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        MovementType movementType = getMovementTypeOrThrow(id);
        movementType.setActive(false);
        movementTypeRepositoryPort.save(movementType);
    }

    @Override
    public MovementType getById(Long id) {
        return getMovementTypeOrThrow(id);
    }

    @Override
    public List<MovementType> getAll() {
        return movementTypeRepositoryPort.findAll();
    }

    private MovementType getMovementTypeOrThrow(Long id) {
        return movementTypeRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "El tipo de movimiento con id " + id + " no existe."));
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre del tipo de movimiento es obligatorio.");
        }
    }
}
