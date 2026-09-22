package com.pruebatecnica.prueba_tecnica.Client.Application.Service;

import com.pruebatecnica.prueba_tecnica.Client.Domain.Model.DocumentType;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.DocumentTypeInputs.CreateDocumentTypeCommand;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.DocumentTypeInputs.DocumentTypeCrudPort;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.DocumentTypeInputs.UpdateDocumentTypeCommand;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Output.DocumentTypeRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeService implements DocumentTypeCrudPort {

    private final DocumentTypeRepositoryPort documentTypeRepositoryPort;

    public DocumentTypeService(
            DocumentTypeRepositoryPort documentTypeRepositoryPort) {

        this.documentTypeRepositoryPort = documentTypeRepositoryPort;
    }

    @Override
    @Transactional
    public DocumentType create(CreateDocumentTypeCommand command) {

        validateData(command.getCode(), command.getName());

        if (documentTypeRepositoryPort
                .findByCode(command.getCode())
                .isPresent()) {

            throw new IllegalArgumentException(
                    "Ya existe un tipo de documento con el código "
                            + command.getCode());
        }

        DocumentType documentType = new DocumentType(
                null,
                command.getCode(),
                command.getName(),
                command.isActive()
        );

        return documentTypeRepositoryPort.save(documentType);
    }

    @Override
    @Transactional
    public DocumentType update(Long id, UpdateDocumentTypeCommand command) {

        validateData(command.getCode(), command.getName());

        DocumentType documentType =
                documentTypeRepositoryPort.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException(
                                "El tipo de documento con id "
                                        + id
                                        + " no existe."
                        ));

        if (documentTypeRepositoryPort
                .findByCode(command.getCode())
                .filter(existing -> !existing.getId().equals(id))
                .isPresent()) {

            throw new IllegalArgumentException(
                    "Ya existe un tipo de documento con el código "
                            + command.getCode());
        }

        documentType.setCode(command.getCode());
        documentType.setName(command.getName());
        documentType.setActive(command.isActive());

        return documentTypeRepositoryPort.save(documentType);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        DocumentType documentType =
                documentTypeRepositoryPort.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException(
                                "El tipo de documento con id "
                                        + id
                                        + " no existe."
                        ));

        documentType.setActive(false);

        documentTypeRepositoryPort.save(documentType);
    }

    @Override
    public DocumentType getById(Long id) {

        return documentTypeRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "El tipo de documento con id "
                                + id
                                + " no existe."
                ));
    }

    @Override
    public List<DocumentType> getAll() {
        return documentTypeRepositoryPort.findAll();
    }

    private void validateData(String code, String name) {

        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException(
                    "El código del tipo de documento es obligatorio.");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre del tipo de documento es obligatorio.");
        }
    }
}
