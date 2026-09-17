package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Controller;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Transfer;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Movimient.FindMovementPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Transfer.FindTransferPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Dto.Response.MovementResponse;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Dto.Response.TransferResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transferencia")
public class TransferController {
    private final FindTransferPort findTransferPort;
    private final FindMovementPort findMovementPort;

    public TransferController(FindTransferPort findTransferPort, FindMovementPort findMovementPort) {
        this.findTransferPort = findTransferPort;
        this.findMovementPort = findMovementPort;
    }

    @GetMapping("/{id}")
    public TransferResponse getById(@PathVariable Long id) {
        return toResponse(findTransferPort.getById(id));
    }

    @GetMapping
    public List<TransferResponse> getAll() {
        return findTransferPort.getAll().stream().map(this::toResponse).toList();
    }

    @GetMapping(params = "movimientoId")
    public TransferResponse getByMovementId(@RequestParam Long movimientoId) {
        return toResponse(findTransferPort.getByOriginMovementId(movimientoId));
    }

    private TransferResponse toResponse(Transfer transfer) {
        MovementResponse origin = MovementResponse.from(findMovementPort.getById(transfer.getOriginMovementId()));
        MovementResponse destination = MovementResponse.from(findMovementPort.getById(transfer.getDestinationMovementId()));
        return TransferResponse.from(transfer, origin, destination);
    }
}
