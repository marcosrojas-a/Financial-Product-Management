package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.CreateUseCase;

public interface CreateFinancialProductPort  {
    FinancialProduct execute(CreateFinancialProductCommand command);
}
