package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento")
public class MovementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_id", nullable = false)
    private Long productId;

    @Column(name = "tipo_movimiento_id", nullable = false)
    private Long movementTypeId;

    @Column(name = "valor", nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    @Column(name = "saldo_anterior", nullable = false, precision = 18, scale = 2)
    private BigDecimal previousBalance;

    @Column(name = "saldo_nuevo", nullable = false, precision = 18, scale = 2)
    private BigDecimal newBalance;

    @Column(name = "descripcion", length = 255)
    private String description;

    @Column(name = "fecha_movimiento", nullable = false)
    private LocalDateTime movementDate;

    protected MovementEntity() {}

    public MovementEntity(Long id, Long productId, Long movementTypeId, BigDecimal amount,
                          BigDecimal previousBalance, BigDecimal newBalance, String description,
                          LocalDateTime movementDate) {
        this.id = id;
        this.productId = productId;
        this.movementTypeId = movementTypeId;
        this.amount = amount;
        this.previousBalance = previousBalance;
        this.newBalance = newBalance;
        this.description = description;
        this.movementDate = movementDate;
    }

    public Long getId() { return id; }
    public Long getProductId() { return productId; }
    public Long getMovementTypeId() { return movementTypeId; }
    public BigDecimal getAmount() { return amount; }
    public BigDecimal getPreviousBalance() { return previousBalance; }
    public BigDecimal getNewBalance() { return newBalance; }
    public String getDescription() { return description; }
    public LocalDateTime getMovementDate() { return movementDate; }
}
