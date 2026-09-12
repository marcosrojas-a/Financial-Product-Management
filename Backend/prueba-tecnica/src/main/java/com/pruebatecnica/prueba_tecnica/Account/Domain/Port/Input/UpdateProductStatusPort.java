package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.UpdateUseCase;

public interface UpdateProductStatusPort extends UpdateUseCase<UpdateProductStatusCommand, FinancialProduct, Long> {
}
