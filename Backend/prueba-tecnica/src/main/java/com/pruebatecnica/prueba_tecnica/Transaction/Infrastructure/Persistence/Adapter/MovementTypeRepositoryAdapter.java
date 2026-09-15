package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Adapter;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.MovementType;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Output.MovementTypeRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Mapper.MovementTypeMapper;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Repository.MovementTypeJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovementTypeRepositoryAdapter implements MovementTypeRepositoryPort {

    private final MovementTypeJpaRepository movementTypeJpaRepository;

    public MovementTypeRepositoryAdapter(MovementTypeJpaRepository movementTypeJpaRepository) {
        this.movementTypeJpaRepository = movementTypeJpaRepository;
    }

    @Override
    public Optional<MovementType> findById(Long id) {
        return movementTypeJpaRepository.findById(id).map(MovementTypeMapper::toDomain);
    }

    @Override
    public Optional<MovementType> findByCode(String code) {
        return movementTypeJpaRepository.findByCodigo(code).map(MovementTypeMapper::toDomain);
    }

    @Override
    public List<MovementType> findAll() {
        return movementTypeJpaRepository.findAll().stream()
                .map(MovementTypeMapper::toDomain)
                .toList();
    }
}