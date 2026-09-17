package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Dto.Request;

import java.math.BigDecimal;

public class ConsignmentRequest {
    private Long productId;
    private BigDecimal amount;
    private String description;

    public ConsignmentRequest() {}

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
