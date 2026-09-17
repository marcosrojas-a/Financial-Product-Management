package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Dto.Request;

import java.math.BigDecimal;

public class TransferRequest {

    private Long originProductId;
    private Long destinationProductId;
    private BigDecimal amount;
    private String description;

    public TransferRequest() {}

    public Long getOriginProductId() { return originProductId; }
    public void setOriginProductId(Long originProductId) { this.originProductId = originProductId; }
    public Long getDestinationProductId() { return destinationProductId; }
    public void setDestinationProductId(Long destinationProductId) { this.destinationProductId = destinationProductId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
