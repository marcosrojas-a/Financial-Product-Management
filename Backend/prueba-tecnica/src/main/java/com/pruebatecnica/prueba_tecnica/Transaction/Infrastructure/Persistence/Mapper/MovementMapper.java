package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Mapper;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Movement;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.MovementType;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Entity.MovementEntity;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Repository.MovementTypeJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class MovementMapper {
    private final MovementTypeJpaRepository movementTypeJpaRepository;

    public MovementMapper(MovementTypeJpaRepository movementTypeJpaRepository) {
        this.movementTypeJpaRepository = movementTypeJpaRepository;
    }

    public Movement toDomain(MovementEntity entity) {
        if (entity == null) return null;

        MovementType movementType = MovementTypeMapper.toDomain(
                movementTypeJpaRepository.findById(entity.getMovementTypeId())
                        .orElseThrow(() -> new IllegalStateException(
                                "No existe el tipo de movimiento con id " + entity.getMovementTypeId())));

        Movement movement = new Movement(
                entity.getProductId(),
                movementType,
                entity.getAmount(),
                entity.getPreviousBalance(),
                entity.getNewBalance(),
                entity.getDescription(),
                entity.getMovementDate());
        movement.setId(entity.getId());
        return movement;
    }

    public MovementEntity toEntity(Movement domain) {
        if (domain == null) return null;
        return new MovementEntity(
                domain.getId(),
                domain.getProductId(),
                domain.getMovementType().getId(),
                domain.getAmount(),
                domain.getPreviousBalance(),
                domain.getNewBalance(),
                domain.getDescription(),
                domain.getMovementDate());
    }
}
