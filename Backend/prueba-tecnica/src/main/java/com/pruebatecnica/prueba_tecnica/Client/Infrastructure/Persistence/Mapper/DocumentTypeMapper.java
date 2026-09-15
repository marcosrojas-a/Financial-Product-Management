package com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Mapper;

import com.pruebatecnica.prueba_tecnica.Client.Domain.Model.DocumentType;
import com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Entity.DocumentTypeEntity;

public class DocumentTypeMapper {
    public  static DocumentType toDomain(DocumentTypeEntity entity){
        if (entity == null)
            return null;

        DocumentType documentType = new DocumentType();
        documentType.setId(entity.getId());
        documentType.setCode(entity.getCodigo());
        documentType.setName(entity.getNombre());
        documentType.setActive(entity.isActivo());
        return documentType;
    }

    public static DocumentTypeEntity toEntity(DocumentType domain){
        if (domain == null)
            return null;
        return new DocumentTypeEntity(domain.getId(),domain.getCode(), domain.getName(), domain.isActive());
    }
}
