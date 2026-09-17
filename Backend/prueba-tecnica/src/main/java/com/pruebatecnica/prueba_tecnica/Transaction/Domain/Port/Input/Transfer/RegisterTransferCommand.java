package com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.Transfer;

import java.math.BigDecimal;

public class RegisterTransferCommand {
    private final Long originProductId;
    private final Long destinationProductId;
    private final BigDecimal amount;
    private final String description;

    public RegisterTransferCommand(Long originProductId, Long destinationProductId, BigDecimal amount, String description) {
        this.originProductId = originProductId;
        this.destinationProductId = destinationProductId;
        this.amount = amount;
        this.description = description;
    }

    public Long getOriginProductId() { return originProductId; }
    public Long getDestinationProductId() { return destinationProductId; }
    public BigDecimal getAmount() { return amount; }
    public String getDescription() { return description; }
}
