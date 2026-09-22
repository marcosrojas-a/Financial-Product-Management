package com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.TransferInputs;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Movement;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Transfer;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.MovimientInputs.RegisterConsignmentCommand;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.FindUseCase;

public interface FindTransferPort extends FindUseCase<Transfer, Long> {
    Transfer getByOriginMovementId(Long movementId);

    interface RegisterConsignmentPort {

        Movement execute(RegisterConsignmentCommand command);
    }
}
