package com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.MovimientInputs;

import java.math.BigDecimal;

public class RegisterWithdrawalCommand {
    private final Long productId;
    private final BigDecimal amount;
    private final String description;

    public RegisterWithdrawalCommand(Long productId, BigDecimal amount, String description) {
        this.productId = productId;
        this.amount = amount;
        this.description = description;
    }

    public Long getProductId() { return productId; }
    public BigDecimal getAmount() { return amount; }
    public String getDescription() { return description; }
}
