package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductFinancialInputs;

public interface CancelFinancialProductPort {
    void execute(Long id); // internamente invoca FinancialProduct.cancelAccount
}
