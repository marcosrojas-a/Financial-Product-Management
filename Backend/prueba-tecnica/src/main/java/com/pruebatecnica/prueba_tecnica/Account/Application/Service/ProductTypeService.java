package com.pruebatecnica.prueba_tecnica.Account.Application.Service;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductType;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductTypeCrudPort;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.ProductTypeRepositoryPort;
import jakarta.transaction.Transactional;

import java.util.List;

public class ProductTypeService implements ProductTypeCrudPort {

    private final ProductTypeRepositoryPort productTypeRepositoryPort;

    public ProductTypeService(
            ProductTypeRepositoryPort productTypeRepositoryPort) {

        this.productTypeRepositoryPort = productTypeRepositoryPort;
    }

    @Override
    @Transactional
    public ProductType create(ProductType productType) {
        if (productType.getCode() == null ||
                productType.getCode().isBlank()) {

            throw new IllegalArgumentException(
                    "El código del tipo de producto es obligatorio.");
        }

        if (productType.getName() == null ||
                productType.getName().isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre del tipo de producto es obligatorio.");
        }

        if (productTypeRepositoryPort
                .findByCode(productType.getCode())
                .isPresent()) {

            throw new IllegalArgumentException(
                    "Ya existe un tipo de producto con el código "
                            + productType.getCode());
        }

        return productTypeRepositoryPort.save(productType);
    }

    @Override
    @Transactional
    public ProductType update(Long id, ProductType productType) {

        ProductType existingProductType =
                productTypeRepositoryPort.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException(
                                "El tipo de producto con id "
                                        + id + " no existe."));

        existingProductType.setCode(productType.getCode());
        existingProductType.setName(productType.getName());
        existingProductType.setActive(productType.isActive());

        return productTypeRepositoryPort.save(existingProductType);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        ProductType productType =
                productTypeRepositoryPort.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException(
                                "El tipo de producto con id "
                                        + id + " no existe."));

        productType.setActive(false);

        productTypeRepositoryPort.save(productType);
    }

    @Override
    public ProductType getById(Long id) {
        return productTypeRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "El tipo de producto con id "
                                + id + " no existe."));
    }

    @Override
    public List<ProductType> getAll() {
        return productTypeRepositoryPort.findAll();
    }
}
