package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductStatusInputs;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductFinancialInputs.UpdateProductStatusCommand;

public interface UpdateProductStatusPort {
    FinancialProduct execute(Long id, UpdateProductStatusCommand command);
}