package com.pruebatecnica.prueba_tecnica.Account.Application.Service;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductStatus;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductStatusInputs.CreateProductStatusCommand;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductStatusInputs.ProductStatusCrudPort;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductStatusInputs.UpdateProductStatusCatalogCommand;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.ProductStatusRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStatusService implements ProductStatusCrudPort {

    private final ProductStatusRepositoryPort productStatusRepositoryPort;

    public ProductStatusService(ProductStatusRepositoryPort productStatusRepositoryPort) {
        this.productStatusRepositoryPort = productStatusRepositoryPort;
    }

    @Override
    @Transactional
    public ProductStatus create(CreateProductStatusCommand command) {
        validateCode(command.getCode());
        validateName(command.getName());

        if (productStatusRepositoryPort.findByCode(command.getCode()).isPresent()) {
            throw new IllegalArgumentException(
                    "Ya existe un estado de producto con el código " + command.getCode());
        }

        ProductStatus productStatus = new ProductStatus(null, command.getCode(), command.getName(), command.isActive());
        return productStatusRepositoryPort.save(productStatus);
    }

    @Override
    @Transactional
    public ProductStatus update(Long id, UpdateProductStatusCatalogCommand command) {
        ProductStatus existing = getProductStatusOrThrow(id);

        validateCode(command.getCode());
        validateName(command.getName());

        existing.setCode(command.getCode());
        existing.setName(command.getName());
        existing.setActive(command.isActive());

        return productStatusRepositoryPort.save(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ProductStatus productStatus = getProductStatusOrThrow(id);
        productStatus.setActive(false);
        productStatusRepositoryPort.save(productStatus);
    }

    @Override
    public ProductStatus getById(Long id) {
        return getProductStatusOrThrow(id);
    }

    @Override
    public List<ProductStatus> getAll() {
        return productStatusRepositoryPort.findAll();
    }

    private ProductStatus getProductStatusOrThrow(Long id) {
        return productStatusRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "El estado de producto con id " + id + " no existe."));
    }

    private void validateCode(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("El código del estado de producto es obligatorio.");
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre del estado de producto es obligatorio.");
        }
    }
}
