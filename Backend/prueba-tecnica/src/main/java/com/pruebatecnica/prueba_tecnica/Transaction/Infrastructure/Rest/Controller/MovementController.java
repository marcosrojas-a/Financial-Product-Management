package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Controller;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Movement;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Transfer;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Movimient.FindMovementPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Movimient.RegisterConsignmentCommand;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Movimient.RegisterWithdrawalCommand;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Movimient.RegisterWithdrawalPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Transfer.FindTransferPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Transfer.RegisterTransferCommand;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Transfer.RegisterTransferPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Dto.Request.ConsignmentRequest;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Dto.Request.TransferRequest;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Dto.Request.WithdrawalRequest;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Dto.Response.MovementResponse;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Dto.Response.TransferResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimiento")
public class MovementController {
    private final FindTransferPort.RegisterConsignmentPort registerConsignmentPort;
    private final RegisterWithdrawalPort registerWithdrawalPort;
    private final RegisterTransferPort registerTransferPort;
    private final FindMovementPort findMovementPort;

    public MovementController(FindTransferPort.RegisterConsignmentPort registerConsignmentPort,
                              RegisterWithdrawalPort registerWithdrawalPort,
                              RegisterTransferPort registerTransferPort,
                              FindMovementPort findMovementPort) {
        this.registerConsignmentPort = registerConsignmentPort;
        this.registerWithdrawalPort = registerWithdrawalPort;
        this.registerTransferPort = registerTransferPort;
        this.findMovementPort = findMovementPort;
    }

    @PostMapping("/consignaciones")
    public ResponseEntity<MovementResponse> consignar(@RequestBody ConsignmentRequest request) {
        RegisterConsignmentCommand command = new RegisterConsignmentCommand(
                request.getProductId(), request.getAmount(), request.getDescription());
        Movement movement = registerConsignmentPort.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(MovementResponse.from(movement));
    }

    @PostMapping("/retiros")
    public ResponseEntity<MovementResponse> retirar(@RequestBody WithdrawalRequest request) {
        RegisterWithdrawalCommand command = new RegisterWithdrawalCommand(
                request.getProductId(), request.getAmount(), request.getDescription());
        Movement movement = registerWithdrawalPort.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(MovementResponse.from(movement));
    }

    @PostMapping("/transferencias")
    public ResponseEntity<TransferResponse> transferir(@RequestBody TransferRequest request) {
        RegisterTransferCommand command = new RegisterTransferCommand(
                request.getOriginProductId(), request.getDestinationProductId(),
                request.getAmount(), request.getDescription());

        Transfer transfer = registerTransferPort.execute(command);

        MovementResponse origin = MovementResponse.from(findMovementPort.getById(transfer.getOriginMovementId()));
        MovementResponse destination = MovementResponse.from(findMovementPort.getById(transfer.getDestinationMovementId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(TransferResponse.from(transfer, origin, destination));
    }

    @GetMapping("/{id}")
    public MovementResponse getById(@PathVariable Long id) {
        return MovementResponse.from(findMovementPort.getById(id));
    }

    @GetMapping
    public List<MovementResponse> getAll() {
        return findMovementPort.getAll().stream().map(MovementResponse::from).toList();
    }

    @GetMapping("/cuenta/{productId}")
    public List<MovementResponse> getByProductId(@PathVariable Long productId) {
        return findMovementPort.getByProductId(productId).stream().map(MovementResponse::from).toList();
    }
}
