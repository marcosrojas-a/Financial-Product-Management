package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Repository;

import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransferJpaRepository extends JpaRepository<TransferEntity, Long> {
    Optional<TransferEntity> findByOriginMovementId(Long originMovementId);
}