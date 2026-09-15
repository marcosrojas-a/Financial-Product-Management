package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Mapper;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Transfer;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Entity.TransferEntity;

public class TransferMapper {
    public static Transfer toDomain(TransferEntity entity) {
        if (entity == null) return null;
        Transfer transfer = new Transfer(
                entity.getOriginMovementId(),
                entity.getDestinationMovementId(),
                entity.getTransferDate());
        transfer.setId(entity.getId());
        return transfer;
    }

    public static TransferEntity toEntity(Transfer domain) {
        if (domain == null) return null;
        return new TransferEntity(
                domain.getId(),
                domain.getOriginMovementId(),
                domain.getDestinationMovementId(),
                domain.getTransferDate());
    }
}
