package com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Repository;

import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Entity.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductTypeJpaRepository extends JpaRepository<ProductTypeEntity, Long> {
    Optional<ProductTypeEntity> findByCodigo(String codigo);
}