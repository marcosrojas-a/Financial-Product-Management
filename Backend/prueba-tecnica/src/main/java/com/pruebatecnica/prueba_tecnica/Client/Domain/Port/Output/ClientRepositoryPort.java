package com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Output;

import com.pruebatecnica.prueba_tecnica.Client.Domain.Model.Client;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Output.BaseRepositoryPort;

import java.util.Optional;

public interface ClientRepositoryPort extends BaseRepositoryPort<Client, Long> {
    Optional<Client> findByDocumentNumber(String documentNumber);
}
