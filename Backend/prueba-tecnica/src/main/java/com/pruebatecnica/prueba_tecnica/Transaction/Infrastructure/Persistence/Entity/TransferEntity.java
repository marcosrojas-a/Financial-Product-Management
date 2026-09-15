package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "transferencia")
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movimiento_origen_id", nullable = false)
    private Long originMovementId;

    @Column(name = "movimiento_destino_id", nullable = false)
    private Long destinationMovementId;

    @Column(name = "fecha_transferencia", nullable = false)
    private LocalDateTime transferDate;

    protected TransferEntity() {}

    public TransferEntity(Long id, Long originMovementId, Long destinationMovementId, LocalDateTime transferDate) {
        this.id = id;
        this.originMovementId = originMovementId;
        this.destinationMovementId = destinationMovementId;
        this.transferDate = transferDate;
    }

    public Long getId() { return id; }
    public Long getOriginMovementId() { return originMovementId; }
    public Long getDestinationMovementId() { return destinationMovementId; }
    public LocalDateTime getTransferDate() { return transferDate; }
}
