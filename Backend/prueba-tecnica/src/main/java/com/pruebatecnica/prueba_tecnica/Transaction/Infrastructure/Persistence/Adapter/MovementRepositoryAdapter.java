package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Adapter;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Movement;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Output.MovementRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Mapper.MovementMapper;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Repository.MovementJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovementRepositoryAdapter implements MovementRepositoryPort {

    private final MovementJpaRepository movementJpaRepository;
    private final MovementMapper movementMapper;

    public MovementRepositoryAdapter(MovementJpaRepository movementJpaRepository,
                                     MovementMapper movementMapper) {
        this.movementJpaRepository = movementJpaRepository;
        this.movementMapper = movementMapper;
    }

    @Override
    public Movement save(Movement movement) {
        return movementMapper.toDomain(movementJpaRepository.save(movementMapper.toEntity(movement)));
    }

    @Override
    public Optional<Movement> findById(Long id) {
        return movementJpaRepository.findById(id).map(movementMapper::toDomain);
    }

    @Override
    public List<Movement> findByProductId(Long productId) {
        return movementJpaRepository.findByProductIdOrderByMovementDateDesc(productId).stream()
                .map(movementMapper::toDomain)
                .toList();
    }

    @Override
    public List<Movement> findAll() {
        return movementJpaRepository.findAll().stream()
                .map(movementMapper::toDomain)
                .toList();
    }
}
