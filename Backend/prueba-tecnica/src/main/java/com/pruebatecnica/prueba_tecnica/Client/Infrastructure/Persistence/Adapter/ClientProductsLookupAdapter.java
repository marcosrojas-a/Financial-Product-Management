package com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Adapter;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.FinancialProductRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Output.ClientProductsLookupPort;
import org.springframework.stereotype.Component;

@Component
public class ClientProductsLookupAdapter implements ClientProductsLookupPort {

    private final FinancialProductRepositoryPort financialProductRepositoryPort;

    public ClientProductsLookupAdapter(FinancialProductRepositoryPort financialProductRepositoryPort) {
        this.financialProductRepositoryPort = financialProductRepositoryPort;
    }

    @Override
    public boolean hasActiveProducts(Long clientId) {
        return financialProductRepositoryPort.existsActiveByClientId(clientId);
    }
}
