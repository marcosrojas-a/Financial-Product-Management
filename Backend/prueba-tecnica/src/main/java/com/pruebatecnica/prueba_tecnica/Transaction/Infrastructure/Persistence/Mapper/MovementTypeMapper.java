package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Mapper;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.MovementType;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Entity.MovementTypeEntity;

public class MovementTypeMapper {
    public static MovementType toDomain(MovementTypeEntity entity) {
        if (entity == null) return null;
        return new MovementType(entity.getId(), entity.getCodigo(), entity.getNombre(), entity.isActivo());
    }

    public static MovementTypeEntity toEntity(MovementType domain) {
        if (domain == null) return null;
        return new MovementTypeEntity(domain.getId(), domain.getCode(), domain.getName(), domain.isActive());
    }
}
