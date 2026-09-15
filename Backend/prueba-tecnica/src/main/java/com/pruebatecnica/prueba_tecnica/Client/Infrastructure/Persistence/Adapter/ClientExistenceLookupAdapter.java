package com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Adapter;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.ClientExistenceLookupPort;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Output.ClientRepositoryPort;

public class ClientExistenceLookupAdapter implements ClientExistenceLookupPort {

    private final ClientRepositoryPort clientRepositoryPort;

    public ClientExistenceLookupAdapter(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public boolean existsById(Long clientId) {
        return clientRepositoryPort.findById(clientId).isPresent();
    }
}
