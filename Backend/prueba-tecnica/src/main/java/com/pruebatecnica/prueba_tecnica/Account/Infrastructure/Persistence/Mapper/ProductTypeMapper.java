package com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Mapper;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductType;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Entity.ProductTypeEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeMapper {
    public  static ProductType toDomain(ProductTypeEntity entity){
        if (entity == null)
            return null;

        ProductType productType = new ProductType();
        productType.setId(entity.getId());
        productType.setCode(entity.getCodigo());
        productType.setName(entity.getNombre());
        productType.setActive(entity.isActivo());
        return productType;
    }

    public static ProductTypeEntity toEntity(ProductType domain){
        if (domain == null)
            return null;
        return new ProductTypeEntity(domain.getId(),domain.getCode(), domain.getName(), domain.isActive());
    }
}
