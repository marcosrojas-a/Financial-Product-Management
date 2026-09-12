package com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model;

import com.pruebatecnica.prueba_tecnica.shared.Domain.Model.BaseEntity;

import java.time.LocalDateTime;

public class Transfer extends BaseEntity {
    private Long originMovementId;
    private Long destinationMovementId;
    private LocalDateTime transferDate;

    protected Transfer() {
        super();
    }

    public Transfer(Long originMovementId,Long destinationMovementId, LocalDateTime transferDate){
        super();

        validateTransfer(originMovementId, destinationMovementId);

        this.originMovementId = originMovementId;
        this.destinationMovementId = destinationMovementId;
        this.transferDate = (transferDate != null) ? transferDate : LocalDateTime.now();
    }

    public void  validateTransfer(Long originMovementId, Long destinationMovementId){
        if (originMovementId == null || destinationMovementId == null) {
            throw new IllegalArgumentException("Toda transferencia debe vincular un movimiento de origen y uno de destino.");
        }
        if (originMovementId.equals(destinationMovementId)) {
            throw new IllegalArgumentException("El movimiento de origen y destino no pueden ser el mismo.");
        }
    }

    //Getters y Setters

    public Long getOriginMovementId() {
        return originMovementId;
    }

    public Long getDestinationMovementId() {
        return destinationMovementId;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

}
