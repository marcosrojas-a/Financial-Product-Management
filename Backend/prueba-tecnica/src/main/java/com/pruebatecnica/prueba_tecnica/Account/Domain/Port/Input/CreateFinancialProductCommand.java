package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input;

import java.math.BigDecimal;

public class CreateFinancialProductCommand {
    private final Long clientId;
    private final Long productTypeId;
    private final BigDecimal initialBalance;
    private final boolean exemptGmf;

    public CreateFinancialProductCommand(Long clientId, Long productTypeId, BigDecimal initialBalance, boolean exemptGmf) {
        this.clientId = clientId;
        this.productTypeId = productTypeId;
        this.initialBalance = initialBalance;
        this.exemptGmf = exemptGmf;
    }

    public Long getClientId() {
        return clientId;
    }
    public Long getProductTypeId() {
        return productTypeId;
    }
    public BigDecimal getInitialBalance() {
        return initialBalance;
    }
    public boolean isExemptGmf() {
        return exemptGmf;
    }
}
