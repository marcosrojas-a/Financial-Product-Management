package com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Repository;

import com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Entity.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentTypeJpaRepository extends JpaRepository<DocumentTypeEntity, Long> {
    Optional<DocumentTypeEntity> findByCodigo(String codigo);
}