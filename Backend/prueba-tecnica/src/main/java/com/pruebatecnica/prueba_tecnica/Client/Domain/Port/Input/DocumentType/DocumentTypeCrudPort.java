package com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.DocumentType;

import com.pruebatecnica.prueba_tecnica.Client.Domain.Model.DocumentType;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.CrudUseCase;

public interface DocumentTypeCrudPort extends CrudUseCase<CreateDocumentTypeCommand, UpdateDocumentTypeCommand, DocumentType, Long> {
}
