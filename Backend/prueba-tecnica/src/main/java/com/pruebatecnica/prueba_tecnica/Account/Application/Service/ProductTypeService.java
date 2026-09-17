package com.pruebatecnica.prueba_tecnica.Account.Application.Service;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductType;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductType.CreateProductTypeCommand;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductType.ProductTypeCrudPort;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductType.UpdateProductTypeCommand;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.ProductTypeRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService implements ProductTypeCrudPort {

    private final ProductTypeRepositoryPort productTypeRepositoryPort;

    public ProductTypeService(
            ProductTypeRepositoryPort productTypeRepositoryPort) {

        this.productTypeRepositoryPort = productTypeRepositoryPort;
    }

    @Override
    @Transactional
    public ProductType create(CreateProductTypeCommand command) {

        if (command.getCode() == null ||
                command.getCode().isBlank()) {

            throw new IllegalArgumentException(
                    "El código del tipo de producto es obligatorio.");
        }

        if (command.getName() == null ||
                command.getName().isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre del tipo de producto es obligatorio.");
        }

        if (productTypeRepositoryPort
                .findByCode(command.getCode())
                .isPresent()) {

            throw new IllegalArgumentException(
                    "Ya existe un tipo de producto con el código "
                            + command.getCode());
        }

        ProductType productType = new ProductType( null, command.getCode(), command.getName(), command.isActive() );

        return productTypeRepositoryPort.save(productType);
    }

    @Override
    @Transactional
    public ProductType update(Long id, UpdateProductTypeCommand command) {

        ProductType existingProductType =
                productTypeRepositoryPort.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException(
                                "El tipo de producto con id "
                                        + id + " no existe."));

        existingProductType.setCode(command.getCode());
        existingProductType.setName(command.getName());
        existingProductType.setActive(command.isActive());

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
