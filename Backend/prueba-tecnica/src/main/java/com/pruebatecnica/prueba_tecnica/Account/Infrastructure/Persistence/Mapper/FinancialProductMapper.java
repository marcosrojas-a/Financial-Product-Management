package com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Mapper;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductStatus;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductType;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Entity.FinancialProductEntity;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Repository.ProductStatusJpaRepository;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Repository.ProductTypeJpaRepository;

public class FinancialProductMapper {
    private final ProductTypeJpaRepository  productTypeJpaRepository;
    private final ProductStatusJpaRepository productStatusJpaRepository;

    public FinancialProductMapper(ProductTypeJpaRepository productTypeJpaRepository,
                                  ProductStatusJpaRepository productStatusJpaRepository) {
        this.productTypeJpaRepository = productTypeJpaRepository;
        this.productStatusJpaRepository = productStatusJpaRepository;
    }

    public FinancialProduct toDomain(FinancialProductEntity entity) {
        if (entity == null) return null;

        ProductType productType = ProductTypeMapper.toDomain(
                productTypeJpaRepository.findById(entity.getProductTypeId()).orElseThrow());
        ProductStatus productStatus = ProductStatusMapper.toDomain(
                productStatusJpaRepository.findById(entity.getProductStatusId()).orElseThrow());

        FinancialProduct product = new FinancialProduct(entity.getClientId(), productType, productStatus,
                entity.getAccountNumber(), entity.getBalance(), entity.isExemptGmf());
        product.setId(entity.getId());
        product.setCreationDate(entity.getCreationDate());
        product.setModificationDate(entity.getModificationDate());
        return product;
    }

    public FinancialProductEntity toEntity(FinancialProduct domain) {
        if (domain == null) return null;
        return new FinancialProductEntity(domain.getId(), domain.getClientId(),
                domain.getProductType().getId(), domain.getProductStatus().getId(),
                domain.getAccountNumber(), domain.getBalance(), domain.getAvailableBalance(),
                domain.isExemptGmf(), domain.getCreationDate(), domain.getModificationDate());
    }

}
