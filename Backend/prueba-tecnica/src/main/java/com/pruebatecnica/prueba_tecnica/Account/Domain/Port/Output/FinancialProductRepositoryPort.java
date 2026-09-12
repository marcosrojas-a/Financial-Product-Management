package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Output.BaseRepositoryPort;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Output.ReadRepositoryPort;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Output.WriteRepositoryPort;

import java.util.List;
import java.util.Optional;

public interface FinancialProductRepositoryPort extends ReadRepositoryPort<FinancialProduct, Long>, WriteRepositoryPort<FinancialProduct> {
    Optional<FinancialProduct> findByAccountNumber(String accountNumber);
    List<FinancialProduct> findByClientId(Long clientId);
    boolean existsActiveByClientId(Long clientId);
}
