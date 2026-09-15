package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.CrudUseCase;

public interface FinancialProductCrudPort extends CrudUseCase<CreateFinancialProductCommand,UpdateProductStatusCommand, FinancialProduct, Long> {
}
