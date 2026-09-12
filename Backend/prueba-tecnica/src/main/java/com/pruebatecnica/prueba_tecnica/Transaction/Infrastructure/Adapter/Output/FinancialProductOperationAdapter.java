package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Adapter.Output;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.FinancialProductRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Movement;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.MovementType;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Output.FinancialProductOperationPort;

import java.math.BigDecimal;

public class FinancialProductOperationAdapter implements FinancialProductOperationPort {

    private final FinancialProductRepositoryPort financialProductRepositoryPort;

    public FinancialProductOperationAdapter(FinancialProductRepositoryPort financialProductRepositoryPort) {
        this.financialProductRepositoryPort = financialProductRepositoryPort;
    }

    @Override
    public Movement debit(Long productId, BigDecimal amount, MovementType movementType, String description) {
        FinancialProduct product = getProductOrThrow(productId);
        Movement movement = product.debit(amount, movementType, description);
        financialProductRepositoryPort.save(product);
        return movement;
    }

    @Override
    public Movement credit(Long productId, BigDecimal amount, MovementType movementType, String description) {
        FinancialProduct product = getProductOrThrow(productId);
        Movement movement = product.credit(amount, movementType, description);
        financialProductRepositoryPort.save(product);
        return movement;
    }

    private FinancialProduct getProductOrThrow(Long productId) {
        return financialProductRepositoryPort.findById(productId)
                .orElseThrow(() ->
                        new IllegalArgumentException("El producto financiero con id " + productId + " no existe."));
    }
}
