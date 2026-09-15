package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Repository;

import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Entity.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementJpaRepository extends JpaRepository<MovementEntity, Long> {
    List<MovementEntity> findByProductId(Long productId);
    List<MovementEntity> findByProductIdOrderByMovementDateDesc(Long productId);
}
