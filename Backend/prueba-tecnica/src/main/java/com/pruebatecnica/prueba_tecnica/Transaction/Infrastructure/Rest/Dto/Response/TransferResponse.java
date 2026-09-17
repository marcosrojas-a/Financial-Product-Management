package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Dto.Response;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Transfer;

import java.time.LocalDateTime;

public class TransferResponse {
    private Long id;
    private LocalDateTime transferDate;
    private MovementResponse originMovement;
    private MovementResponse destinationMovement;

    public static TransferResponse from(Transfer transfer, MovementResponse origin, MovementResponse destination) {
        TransferResponse response = new TransferResponse();
        response.id = transfer.getId();
        response.transferDate = transfer.getTransferDate();
        response.originMovement = origin;
        response.destinationMovement = destination;
        return response;
    }

    public Long getId() { return id; }
    public LocalDateTime getTransferDate() { return transferDate; }
    public MovementResponse getOriginMovement() { return originMovement; }
    public MovementResponse getDestinationMovement() { return destinationMovement; }
}