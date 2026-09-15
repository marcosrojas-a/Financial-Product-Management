package com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Repository;

import com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientJpaRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByDocumentNumber(String documentNumber);
}