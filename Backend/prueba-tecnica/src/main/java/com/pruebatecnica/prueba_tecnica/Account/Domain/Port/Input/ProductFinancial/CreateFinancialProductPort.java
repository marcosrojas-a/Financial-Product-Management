package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductFinancial;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;

public interface CreateFinancialProductPort  {
    FinancialProduct execute(CreateFinancialProductCommand command);
}
