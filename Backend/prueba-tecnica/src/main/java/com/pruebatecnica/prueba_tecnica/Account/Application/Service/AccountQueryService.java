package com.pruebatecnica.prueba_tecnica.Account.Application.Service;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.FindFinancialProductPort;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.FinancialProductRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountQueryService implements FindFinancialProductPort {

    private final FinancialProductRepositoryPort financialProductRepositoryPort;

    public AccountQueryService(FinancialProductRepositoryPort financialProductRepositoryPort) {
        this.financialProductRepositoryPort = financialProductRepositoryPort;
    }

    @Override
    public FinancialProduct getById(Long id) {
        return financialProductRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El producto financiero con id " + id + " no existe."));
    }

    @Override
    public List<FinancialProduct> getAll() {
        return financialProductRepositoryPort.findAll();
    }

    @Override
    public FinancialProduct getByAccountNumber(String accountNumber) {
        return financialProductRepositoryPort.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe una cuenta con el número " + accountNumber));
    }

    @Override
    public List<FinancialProduct> getByClientId(Long clientId) {
        return financialProductRepositoryPort.findByClientId(clientId);
    }
}
