package com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Adapter;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductStatus;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.ProductStatusRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Entity.ProductStatusEntity;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Mapper.ProductStatusMapper;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Repository.ProductStatusJpaRepository;
import com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Persistence.Adapter.AbstractCrudRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductStatusRepositoryAdapter extends AbstractCrudRepositoryAdapter<ProductStatus, ProductStatusEntity, Long>
        implements ProductStatusRepositoryPort {

    private final ProductStatusJpaRepository productStatusJpaRepository;

    public ProductStatusRepositoryAdapter(ProductStatusJpaRepository productStatusJpaRepository) {
        super(productStatusJpaRepository, ProductStatusMapper::toDomain, ProductStatusMapper::toEntity);
        this.productStatusJpaRepository = productStatusJpaRepository;
    }

    @Override
    public Optional<ProductStatus> findByCode(String code) {
        return productStatusJpaRepository.findByCodigo(code).map(ProductStatusMapper::toDomain);
    }
}
