package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Adapter;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Movement;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Output.MovementRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Entity.MovementEntity;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Mapper.MovementMapper;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Repository.MovementJpaRepository;
import com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Persistence.Adapter.AbstractCrudRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovementRepositoryAdapter extends AbstractCrudRepositoryAdapter<Movement, MovementEntity, Long> implements MovementRepositoryPort {

    private final MovementJpaRepository movementJpaRepository;
    private final MovementMapper movementMapper;

    public MovementRepositoryAdapter(MovementJpaRepository movementJpaRepository,
                                     MovementMapper movementMapper) {
        super(movementJpaRepository, movementMapper::toDomain , movementMapper::toEntity);
        this.movementJpaRepository = movementJpaRepository;
        this.movementMapper = movementMapper;

    }

    @Override
    public List<Movement> findByProductId(Long productId) {
        return movementJpaRepository.findByProductIdOrderByMovementDateDesc(productId).stream()
                .map(movementMapper::toDomain)
                .toList();
    }

}
