package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input;

public interface CancelFinancialProductPort {
    void execute(Long id); // internamente invoca FinancialProduct.cancelAccount
}
