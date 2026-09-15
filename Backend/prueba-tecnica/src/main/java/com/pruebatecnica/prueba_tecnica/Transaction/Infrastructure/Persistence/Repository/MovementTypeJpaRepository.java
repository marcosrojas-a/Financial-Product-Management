package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Repository;

import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Entity.MovementTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovementTypeJpaRepository extends JpaRepository<MovementTypeEntity, Long> {
    Optional<MovementTypeEntity> findByCodigo(String codigo);
}
