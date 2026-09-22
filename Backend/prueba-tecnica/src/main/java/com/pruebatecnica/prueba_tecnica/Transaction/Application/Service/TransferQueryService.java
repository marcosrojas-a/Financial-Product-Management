package com.pruebatecnica.prueba_tecnica.Transaction.Application.Service;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Transfer;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.TransferInputs.FindTransferPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Output.TransferRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferQueryService implements FindTransferPort {

    private final TransferRepositoryPort transferRepositoryPort;

    public TransferQueryService(TransferRepositoryPort transferRepositoryPort) {
        this.transferRepositoryPort = transferRepositoryPort;
    }

    @Override
    public Transfer getById(Long id) {
        return transferRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La transferencia con id " + id + " no existe."));
    }

    @Override
    public List<Transfer> getAll() {
        return transferRepositoryPort.findAll();
    }

    @Override
    public Transfer getByOriginMovementId(Long movementId) {
        return transferRepositoryPort.findByOriginMovementId(movementId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe una transferencia asociada al movimiento con id " + movementId));
    }
}
