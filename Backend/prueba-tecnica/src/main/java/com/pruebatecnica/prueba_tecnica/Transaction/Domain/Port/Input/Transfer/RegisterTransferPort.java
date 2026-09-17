package com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Transfer;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Transfer;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.CreateUseCase;

public interface RegisterTransferPort extends CreateUseCase<RegisterTransferCommand, Transfer> {
}
