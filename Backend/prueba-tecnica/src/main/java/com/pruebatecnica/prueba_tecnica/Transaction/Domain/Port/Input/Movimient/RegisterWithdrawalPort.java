package com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Movimient;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Movement;

public interface RegisterWithdrawalPort{
    Movement execute(RegisterWithdrawalCommand command);
}
