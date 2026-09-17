package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Controller;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.MovementType;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.MovientType.CreateMovementTypeCommand;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.MovientType.MovementTypeCrudPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.MovientType.UpdateMovementTypeCommand;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Dto.Request.MovementTypeRequest;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Dto.Response.MovementTypeResponse;
import com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Rest.Controller.AbstractCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movement-type")
public class MovementTypeController extends AbstractCrudController<CreateMovementTypeCommand, UpdateMovementTypeCommand, MovementType, Long, MovementTypeRequest, MovementTypeResponse> {

    public MovementTypeController(MovementTypeCrudPort movementTypeCrudPort) {
        super(
                movementTypeCrudPort,

                request -> new CreateMovementTypeCommand(
                        request.getCode(), request.getName(), request.isActive()),

                request -> new UpdateMovementTypeCommand(
                        request.getCode(), request.getName(), request.isActive()),

                movementType -> new MovementTypeResponse(
                        movementType.getId(), movementType.getCode(),
                        movementType.getName(), movementType.isActive())
        );
    }
}
