package com.pruebatecnica.prueba_tecnica.Client.Application.Service;

import com.pruebatecnica.prueba_tecnica.Client.Domain.Model.Client;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.FindClientPort;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Output.ClientRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientQueryService implements FindClientPort {

    private final ClientRepositoryPort clientRepositoryPort;

    public ClientQueryService(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public Client getById(Long id) {
        return clientRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El cliente con id " + id + " no existe."));
    }

    @Override
    public List<Client> getAll() {
        return clientRepositoryPort.findAll();
    }

    @Override
    public Client getByDocument(String documentNumber) {
        return clientRepositoryPort.findByDocumentNumber(documentNumber)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe un cliente con el documento " + documentNumber));
    }
}
