package com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Movement;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.CreateUseCase;

public interface RegisterWithdrawalPort extends CreateUseCase<RegisterWithdrawalCommand, Movement> {
}
