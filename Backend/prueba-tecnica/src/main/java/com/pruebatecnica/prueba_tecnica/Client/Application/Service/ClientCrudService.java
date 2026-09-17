package com.pruebatecnica.prueba_tecnica.Client.Application.Service;

import com.pruebatecnica.prueba_tecnica.Client.Domain.Model.Client;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.Client.CreateClientCommand;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.Client.FindClientByDocumentPort;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.Client.UpdateClientCommand;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Output.ClientProductsLookupPort;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Output.ClientRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Output.DocumentTypeRepositoryPort;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.CrudUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientCrudService implements CrudUseCase<CreateClientCommand, UpdateClientCommand, Client, Long>, FindClientByDocumentPort {

        private final ClientRepositoryPort clientRepositoryPort;
        private final DocumentTypeRepositoryPort documentTypeRepositoryPort;
        private final ClientProductsLookupPort clientProductsLookupPort;

    public ClientCrudService(ClientRepositoryPort clientRepositoryPort,
                DocumentTypeRepositoryPort documentTypeRepositoryPort,
                ClientProductsLookupPort clientProductsLookupPort) {
            this.clientRepositoryPort = clientRepositoryPort;
            this.documentTypeRepositoryPort = documentTypeRepositoryPort;
            this.clientProductsLookupPort = clientProductsLookupPort;
        }

        @Override
        @Transactional
        public Client create(CreateClientCommand command) {
            validateDocumentTypeExists(command.getDocumentTypeId());
            validateDocumentNumberNotTaken(command.getDocumentNumber());

            Client client = new Client(
                    command.getDocumentTypeId(), command.getDocumentNumber(),
                    command.getNames(), command.getLastNames(),
                    command.getEmail(), command.getBirthDate());

            return clientRepositoryPort.save(client);
        }

        @Override
        @Transactional
        public Client update(Long id, UpdateClientCommand command) {
            Client client = getClientOrThrow(id);

            validateDocumentTypeExists(command.getDocumentTypeId());
            validateDocumentNumberNotTakenByOther(id, command.getDocumentNumber());

            client.setDocumentTypeId(command.getDocumentTypeId());
            client.setDocumentNumber(command.getDocumentNumber());
            client.setFirstName(command.getNames());
            client.setLastName(command.getLastNames());
            client.setEmail(command.getEmail());

            return clientRepositoryPort.save(client);
        }

        @Override
        @Transactional
        public void delete(Long id) {
            getClientOrThrow(id);

            if (clientProductsLookupPort.hasActiveProducts(id)) {
                throw new IllegalStateException(
                        "No se puede eliminar el cliente: tiene productos financieros asociados.");
            }

            clientRepositoryPort.deleteById(id);
        }

        @Override
        public Client getById(Long id) {
            return getClientOrThrow(id);
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

        private Client getClientOrThrow(Long id) {
            return clientRepositoryPort.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("El cliente con id " + id + " no existe."));
        }

        private void validateDocumentTypeExists(Long documentTypeId) {
            documentTypeRepositoryPort.findById(documentTypeId)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "El tipo de documento con id " + documentTypeId + " no existe."));
        }

        private void validateDocumentNumberNotTaken(String documentNumber) {
            clientRepositoryPort.findByDocumentNumber(documentNumber).ifPresent(existing -> {
                throw new IllegalArgumentException(
                        "Ya existe un cliente registrado con el documento " + documentNumber);
            });
        }

        private void validateDocumentNumberNotTakenByOther(Long clientId, String documentNumber) {
            clientRepositoryPort.findByDocumentNumber(documentNumber).ifPresent(existing -> {
                if (!existing.getId().equals(clientId)) {
                    throw new IllegalArgumentException(
                            "Ya existe otro cliente registrado con el documento " + documentNumber);
                }
            });
        }
}
