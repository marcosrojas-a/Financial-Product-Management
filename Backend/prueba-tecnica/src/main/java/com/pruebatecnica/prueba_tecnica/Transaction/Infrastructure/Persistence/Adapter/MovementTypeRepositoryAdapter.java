package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Adapter;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.MovementType;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Output.MovementTypeRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Entity.MovementTypeEntity;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Mapper.MovementTypeMapper;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Repository.MovementTypeJpaRepository;
import com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Persistence.Adapter.AbstractCrudRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovementTypeRepositoryAdapter extends AbstractCrudRepositoryAdapter<MovementType, MovementTypeEntity, Long>
        implements MovementTypeRepositoryPort {

    private final MovementTypeJpaRepository movementTypeJpaRepository;


    public MovementTypeRepositoryAdapter(MovementTypeJpaRepository movementTypeJpaRepository) {
        super(movementTypeJpaRepository, MovementTypeMapper::toDomain, MovementTypeMapper::toEntity);
        this.movementTypeJpaRepository = movementTypeJpaRepository;
    }

    @Override
    public Optional<MovementType> findByCode(String code) {
        return movementTypeJpaRepository.findByCodigo(code).map(MovementTypeMapper::toDomain);
    }


}