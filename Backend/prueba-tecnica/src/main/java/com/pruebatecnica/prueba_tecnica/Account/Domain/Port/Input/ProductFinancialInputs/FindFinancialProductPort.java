package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductFinancialInputs;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;

import java.util.List;

public interface FindFinancialProductPort{
    FinancialProduct getByAccountNumber(String accountNumber);

    List<FinancialProduct> getByClientId(Long clientId);
}
