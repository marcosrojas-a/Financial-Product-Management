package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductStatus;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductFinancial.UpdateProductStatusCommand;

public interface UpdateProductStatusPort {
    FinancialProduct execute(Long id, UpdateProductStatusCommand command);
}