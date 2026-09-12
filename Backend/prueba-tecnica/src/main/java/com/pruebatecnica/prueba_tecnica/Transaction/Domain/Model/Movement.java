package com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductType;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Model.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Movement extends BaseEntity {
    private Long productId;
    private MovementType movementType;
    private BigDecimal amount;
    private BigDecimal previousBalance;
    private BigDecimal newBalance;
    private String description;
    private LocalDateTime movementDate;

    protected Movement(){
        super();
    }

    public Movement(Long productId, MovementType movementType, BigDecimal amount,
                    BigDecimal previousBalance, BigDecimal newBalance,String description, LocalDateTime movementDate){
        super();

        if (productId == null) {
            throw new IllegalArgumentException("El movimiento debe estar asociado a una cuenta bancaria.");
        }

        this.productId = productId;
        this.movementType = movementType;
        this.amount = amount;
        this.previousBalance = previousBalance;
        this.newBalance = newBalance;
        this.description = description;
        this.movementDate = (movementDate != null) ? movementDate : LocalDateTime.now();
    }

    // Getters
    public Long getProductId() {
        return productId;
    }
    public MovementType getMovementType() {
        return movementType;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public BigDecimal getPreviousBalance() {
        return previousBalance;
    }
    public BigDecimal getNewBalance() {
        return newBalance;
    }
    public String getDescription() {
        return description;
    }
    public LocalDateTime getMovementDate() {
        return movementDate;
    }
}
