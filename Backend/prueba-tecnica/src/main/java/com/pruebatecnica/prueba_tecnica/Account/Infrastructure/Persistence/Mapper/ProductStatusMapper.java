package com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Mapper;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductStatus;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductType;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Entity.ProductStatusEntity;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Entity.ProductTypeEntity;

public class ProductStatusMapper {
    public  static ProductStatus toDomain(ProductStatusEntity entity){
        if (entity == null)
            return null;

        ProductStatus productStatus = new ProductStatus();
        productStatus.setId(entity.getId());
        productStatus.setCode(entity.getCodigo());
        productStatus.setName(entity.getNombre());
        productStatus.setActive(entity.isActivo());
        return productStatus;
    }

    public static ProductStatusEntity toEntity(ProductStatus domain){
        if (domain == null)
            return null;
        return new ProductStatusEntity(domain.getId(),domain.getCode(), domain.getName(), domain.isActive());
    }
}
