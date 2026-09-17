package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Dto.Response;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Movement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovementResponse {

    private Long id;
    private Long productId;
    private String movementType;
    private BigDecimal amount;
    private BigDecimal previousBalance;
    private BigDecimal newBalance;
    private String description;
    private LocalDateTime movementDate;

    public static MovementResponse from(Movement movement) {
        MovementResponse response = new MovementResponse();
        response.id = movement.getId();
        response.productId = movement.getProductId();
        response.movementType = movement.getMovementType().getName();
        response.amount = movement.getAmount();
        response.previousBalance = movement.getPreviousBalance();
        response.newBalance = movement.getNewBalance();
        response.description = movement.getDescription();
        response.movementDate = movement.getMovementDate();
        return response;
    }

    public Long getId() { return id; }
    public Long getProductId() { return productId; }
    public String getMovementType() { return movementType; }
    public BigDecimal getAmount() { return amount; }
    public BigDecimal getPreviousBalance() { return previousBalance; }
    public BigDecimal getNewBalance() { return newBalance; }
    public String getDescription() { return description; }
    public LocalDateTime getMovementDate() { return movementDate; }
}
