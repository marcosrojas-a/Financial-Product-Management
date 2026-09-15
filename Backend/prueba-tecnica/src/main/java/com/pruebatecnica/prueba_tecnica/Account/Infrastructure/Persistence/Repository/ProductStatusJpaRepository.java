package com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Repository;

import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Entity.ProductStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductStatusJpaRepository extends JpaRepository<ProductStatusEntity, Long> {
    Optional<ProductStatusEntity> findByCodigo(String codigo);
}