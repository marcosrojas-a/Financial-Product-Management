package com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Rest.Dto.Request;

import java.math.BigDecimal;

public class FinancialProductRequest {
    private Long clientId;

    private Long productTypeId;

    private BigDecimal initialBalance;

    private Boolean exemptGmf;

    private Long newStatusId;

    public FinancialProductRequest() {
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Boolean getExemptGmf() {
        return exemptGmf;
    }

    public void setExemptGmf(Boolean exemptGmf) {
        this.exemptGmf = exemptGmf;
    }

    public Long getNewStatusId() {
        return newStatusId;
    }

    public void setNewStatusId(Long newStatusId) {
        this.newStatusId = newStatusId;
    }
}
