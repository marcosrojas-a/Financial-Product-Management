package com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Adapter;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductType;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.ProductTypeRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Entity.ProductTypeEntity;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Mapper.ProductTypeMapper;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Repository.ProductTypeJpaRepository;
import com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Persistence.Adapter.AbstractCrudRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductTypeRepositoryAdapter extends AbstractCrudRepositoryAdapter<ProductType, ProductTypeEntity, Long>
        implements ProductTypeRepositoryPort {

    private final ProductTypeJpaRepository productTypeJpaRepository;

    public ProductTypeRepositoryAdapter(ProductTypeJpaRepository productTypeJpaRepository) {
        super(productTypeJpaRepository, ProductTypeMapper::toDomain, ProductTypeMapper::toEntity);
        this.productTypeJpaRepository = productTypeJpaRepository;
    }

    @Override
    public Optional<ProductType> findByCode(String code) {
        return productTypeJpaRepository.findByCodigo(code).map(ProductTypeMapper::toDomain);
    }
}
