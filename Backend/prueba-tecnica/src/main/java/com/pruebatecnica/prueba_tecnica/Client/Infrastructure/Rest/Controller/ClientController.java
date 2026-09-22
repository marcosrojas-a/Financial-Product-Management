package com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Rest.Controller;

import com.pruebatecnica.prueba_tecnica.Client.Domain.Model.Client;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.ClientInputs.CreateClientCommand;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.ClientInputs.FindClientByDocumentPort;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.ClientInputs.UpdateClientCommand;
import com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Rest.Dto.Request.ClientRequest;
import com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Rest.Dto.Response.ClientResponse;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.CrudUseCase;
import com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Rest.Controller.AbstractCrudController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClientController extends AbstractCrudController<CreateClientCommand, UpdateClientCommand, Client, Long, ClientRequest, ClientResponse> {

    private final FindClientByDocumentPort findClientByDocumentPort;

    public ClientController(CrudUseCase<CreateClientCommand, UpdateClientCommand, Client, Long> crudUseCase,
                            FindClientByDocumentPort findClientByDocumentPort) {
        super(crudUseCase, ClientController::toCreateCommand, ClientController::toUpdateCommand, ClientResponse::from);
        this.findClientByDocumentPort = findClientByDocumentPort;
    }

    @GetMapping(params = "numeroDocumento")
    public ClientResponse getByDocumento(@RequestParam String numeroDocumento) {
        return ClientResponse.from(findClientByDocumentPort.getByDocument(numeroDocumento));
    }

    private static CreateClientCommand toCreateCommand(ClientRequest request) {
        return new CreateClientCommand(
                request.getTipoDocumentoId(), request.getNumeroDocumento(),
                request.getNombres(), request.getApellidos(),
                request.getCorreo(), request.getFechaNacimiento());
    }

    private static UpdateClientCommand toUpdateCommand(ClientRequest request) {
        return new UpdateClientCommand(
                request.getTipoDocumentoId(), request.getNumeroDocumento(),
                request.getNombres(), request.getApellidos(), request.getCorreo());
    }
}
