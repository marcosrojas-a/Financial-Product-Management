package com.pruebatecnica.prueba_tecnica.Transaction.Application.Service;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Movement;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.MovementType;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Transfer;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Movimient.RegisterConsignmentCommand;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Movimient.RegisterWithdrawalCommand;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Movimient.RegisterWithdrawalPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Transfer.FindTransferPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Transfer.RegisterTransferCommand;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Transfer.RegisterTransferPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Output.FinancialProductOperationPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Output.MovementRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Output.MovementTypeRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Output.TransferRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionApplicationService implements FindTransferPort.RegisterConsignmentPort, RegisterWithdrawalPort, RegisterTransferPort {

    private final FinancialProductOperationPort financialProductOperationPort;
    private final MovementRepositoryPort movementRepositoryPort;
    private final TransferRepositoryPort transferRepositoryPort;
    private final MovementTypeRepositoryPort movementTypeRepositoryPort;

    public TransactionApplicationService(FinancialProductOperationPort financialProductOperationPort,
                                         MovementRepositoryPort movementRepositoryPort,
                                         TransferRepositoryPort transferRepositoryPort,
                                         MovementTypeRepositoryPort movementTypeRepositoryPort) {
        this.financialProductOperationPort = financialProductOperationPort;
        this.movementRepositoryPort = movementRepositoryPort;
        this.transferRepositoryPort = transferRepositoryPort;
        this.movementTypeRepositoryPort = movementTypeRepositoryPort;
    }

    @Override
    @Transactional
    public Movement execute(RegisterConsignmentCommand command) {
        validateCommand(command.getProductId(), command.getAmount());
        MovementType movementType = getMovementTypeOrThrow(MovementType.CONSIGNMENT);
        Movement movement = financialProductOperationPort.credit(
                command.getProductId(), command.getAmount(), movementType, command.getDescription());
        return movementRepositoryPort.save(movement);
    }

    @Override
    @Transactional
    public Movement execute(RegisterWithdrawalCommand command) {
        validateCommand(command.getProductId(), command.getAmount());
        MovementType movementType = getMovementTypeOrThrow(MovementType.WITHDRAWAL);
        Movement movement = financialProductOperationPort.debit(
                command.getProductId(), command.getAmount(), movementType, command.getDescription());
        return movementRepositoryPort.save(movement);
    }

    @Override
    @Transactional
    public Transfer execute(RegisterTransferCommand command) {
        validateCommand(command.getOriginProductId(), command.getAmount());
        validateCommand(command.getDestinationProductId(), command.getAmount());
        validateDifferentProducts(command);

        MovementType movementType = getMovementTypeOrThrow(MovementType.TRANSFER);

        Movement originMovement = financialProductOperationPort.debit(
                command.getOriginProductId(), command.getAmount(), movementType, command.getDescription());
        Movement savedOriginMovement = movementRepositoryPort.save(originMovement);

        Movement destinationMovement = financialProductOperationPort.credit(
                command.getDestinationProductId(), command.getAmount(), movementType, command.getDescription());
        Movement savedDestinationMovement = movementRepositoryPort.save(destinationMovement);

        Transfer transfer = new Transfer(
                savedOriginMovement.getId(), savedDestinationMovement.getId(), LocalDateTime.now());
        return transferRepositoryPort.save(transfer);
    }

    private void validateDifferentProducts(RegisterTransferCommand command) {
        if (command.getOriginProductId().equals(command.getDestinationProductId())) {
            throw new IllegalArgumentException("La cuenta de origen y destino no pueden ser la misma.");
        }
    }

    private MovementType getMovementTypeOrThrow(String code) {
        return movementTypeRepositoryPort.findByCode(code)
                .orElseThrow(() -> new IllegalStateException(
                        "Catálogo de tipos de movimiento no inicializado: falta " + code));
    }

    private void validateCommand(Long productId, BigDecimal amount) {
        if (productId == null) {
            throw new IllegalArgumentException("El producto financiero es obligatorio.");
        }
        if (amount == null) {
            throw new IllegalArgumentException("El monto de la transacción es obligatorio.");
        }
    }
}
