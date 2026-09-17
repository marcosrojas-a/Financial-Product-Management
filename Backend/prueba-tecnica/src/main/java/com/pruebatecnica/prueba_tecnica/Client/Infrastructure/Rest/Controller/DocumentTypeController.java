package com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Rest.Controller;

import com.pruebatecnica.prueba_tecnica.Client.Domain.Model.DocumentType;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.DocumentType.CreateDocumentTypeCommand;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.DocumentType.DocumentTypeCrudPort;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.DocumentType.UpdateDocumentTypeCommand;
import com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Rest.Dto.Request.DocumentTypeRequest;
import com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Rest.Dto.Response.DocumentTypeResponse;
import com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Rest.Controller.AbstractCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/document-type")
public class DocumentTypeController  extends AbstractCrudController<CreateDocumentTypeCommand, UpdateDocumentTypeCommand, DocumentType, Long, DocumentTypeRequest, DocumentTypeResponse> {

    public DocumentTypeController(
            DocumentTypeCrudPort documentTypeCrudPort) {

        super(
                documentTypeCrudPort,

                request -> new CreateDocumentTypeCommand(
                        request.getCode(),
                        request.getName(),
                        request.isActive()
                ),

                request -> new UpdateDocumentTypeCommand(
                        request.getCode(),
                        request.getName(),
                        request.isActive()
                ),

                documentType -> new DocumentTypeResponse(
                        documentType.getId(),
                        documentType.getCode(),
                        documentType.getName(),
                        documentType.isActive()
                )
        );
    }
}
