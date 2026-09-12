package com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Output;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Movement;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.MovementType;

import java.math.BigDecimal;

public interface FinancialProductOperationPort {
    // Implementado por un adaptador que opera sobre FinancialProduct
    // y devuelve el Movement ya generado por las reglas de negocio de la cuenta.
    Movement debit(Long productId, BigDecimal amount, MovementType movementType, String description);
    Movement credit(Long productId, BigDecimal amount, MovementType movementType, String description);
}
