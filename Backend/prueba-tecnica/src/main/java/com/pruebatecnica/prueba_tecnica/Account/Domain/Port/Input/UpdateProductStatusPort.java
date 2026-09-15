package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;

public interface UpdateProductStatusPort {
    FinancialProduct execute(Long id, UpdateProductStatusCommand command);
}