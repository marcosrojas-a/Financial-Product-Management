package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.FindUseCase;

import java.util.List;

public interface FindFinancialProductPort extends FindUseCase<FinancialProduct, Long> {
    FinancialProduct getByAccountNumber(String accountNumber);
    List<FinancialProduct> getByClientId(Long clientId);
}
