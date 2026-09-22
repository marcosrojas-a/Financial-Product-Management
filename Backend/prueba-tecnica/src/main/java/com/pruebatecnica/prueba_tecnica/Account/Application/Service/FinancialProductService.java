package com.pruebatecnica.prueba_tecnica.Account.Application.Service;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductStatus;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductType;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductFinancialInputs.CreateFinancialProductCommand;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductFinancialInputs.FinancialProductCrudPort;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductFinancialInputs.FindFinancialProductPort;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductFinancialInputs.UpdateProductStatusCommand;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.ClientExistenceLookupPort;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.FinancialProductRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.ProductStatusRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.ProductTypeRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class FinancialProductService implements FinancialProductCrudPort, FindFinancialProductPort {

    private static final SecureRandom RANDOM =
            new SecureRandom();

    private static final int ACCOUNT_NUMBER_CORRELATIVE_LENGTH = 8;

    private final FinancialProductRepositoryPort
            financialProductRepositoryPort;

    private final ProductTypeRepositoryPort
            productTypeRepositoryPort;

    private final ProductStatusRepositoryPort
            productStatusRepositoryPort;

    private final ClientExistenceLookupPort
            clientExistenceLookupPort;

    public FinancialProductService(
            FinancialProductRepositoryPort financialProductRepositoryPort,
            ProductTypeRepositoryPort productTypeRepositoryPort,
            ProductStatusRepositoryPort productStatusRepositoryPort,
            ClientExistenceLookupPort clientExistenceLookupPort) {

        this.financialProductRepositoryPort =
                financialProductRepositoryPort;

        this.productTypeRepositoryPort =
                productTypeRepositoryPort;

        this.productStatusRepositoryPort =
                productStatusRepositoryPort;

        this.clientExistenceLookupPort =
                clientExistenceLookupPort;
    }

    // CREAR

    @Override
    @Transactional
    public FinancialProduct create(
            CreateFinancialProductCommand command) {

        validateClientExists(command.getClientId());

        ProductType productType =
                productTypeRepositoryPort
                        .findById(command.getProductTypeId())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "El tipo de producto con id "
                                                + command.getProductTypeId()
                                                + " no existe."
                                )
                        );

        ProductStatus activeStatus =
                productStatusRepositoryPort
                        .findByCode(ProductStatus.ACTIVE)
                        .orElseThrow(() ->
                                new IllegalStateException(
                                        "Catálogo de estados no inicializado: falta "
                                                + ProductStatus.ACTIVE
                                )
                        );

        String accountNumber =
                generateUniqueAccountNumber(productType);

        FinancialProduct product =
                new FinancialProduct(
                        command.getClientId(),
                        productType,
                        activeStatus,
                        accountNumber,
                        command.getInitialBalance(),
                        command.isExemptGmf()
                );

        return financialProductRepositoryPort.save(product);
    }

    // Actualizar

    @Override
    @Transactional
    public FinancialProduct update(
            Long id,
            UpdateProductStatusCommand command) {

        FinancialProduct product =
                getProductOrThrow(id);

        ProductStatus newStatus =
                productStatusRepositoryPort
                        .findById(command.getNewStatusId())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "El estado con id "
                                                + command.getNewStatusId()
                                                + " no existe."
                                )
                        );

        product.setProductStatus(newStatus);

        return financialProductRepositoryPort.save(product);
    }


    // ELIMINAR


    @Override
    @Transactional
    public void delete(Long id) {

        FinancialProduct product =
                getProductOrThrow(id);

        ProductStatus cancelledStatus =
                productStatusRepositoryPort
                        .findByCode(ProductStatus.CANCELLED)
                        .orElseThrow(() ->
                                new IllegalStateException(
                                        "Catálogo de estados no inicializado: falta "
                                                + ProductStatus.CANCELLED
                                )
                        );

        product.cancelAccount(cancelledStatus);

        financialProductRepositoryPort.save(product);
    }


    // BUSCAR POR ID


    @Override
    public FinancialProduct getById(Long id) {

        return financialProductRepositoryPort
                .findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El producto financiero con id "
                                        + id
                                        + " no existe."
                        )
                );
    }


    // BUSCAR TODA LA LISTA
    // =========================================================

    @Override
    public List<FinancialProduct> getAll() {

        return financialProductRepositoryPort.findAll();
    }

    // BUSCAR POR NUMERO DE CUENTA


    @Override
    public FinancialProduct getByAccountNumber(
            String accountNumber) {

        return financialProductRepositoryPort
                .findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No existe un producto financiero "
                                        + "con el número de cuenta "
                                        + accountNumber
                        )
                );
    }


    // BUSCAR POR CLIENTE

    @Override
    public List<FinancialProduct> getByClientId(
            Long clientId) {

        return financialProductRepositoryPort
                .findByClientId(clientId);
    }


    // METODOS PRIVADOS

    private FinancialProduct getProductOrThrow(Long id) {

        return financialProductRepositoryPort
                .findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El producto financiero con id "
                                        + id
                                        + " no existe."
                        )
                );
    }

    private void validateClientExists(Long clientId) {

        if (!clientExistenceLookupPort.existsById(clientId)) {

            throw new IllegalArgumentException(
                    "El cliente con id "
                            + clientId
                            + " no existe."
            );
        }
    }

    private String generateUniqueAccountNumber(
            ProductType productType) {

        String prefix =
                productType.getAccountNumberPrefix();

        String accountNumber;

        do {

            accountNumber =
                    prefix +
                            randomDigits(
                                    ACCOUNT_NUMBER_CORRELATIVE_LENGTH
                            );

        } while (
                financialProductRepositoryPort
                        .findByAccountNumber(accountNumber)
                        .isPresent()
        );

        return accountNumber;
    }

    private String randomDigits(int length) {

        StringBuilder result =
                new StringBuilder(length);

        for (int i = 0; i < length; i++) {

            result.append(
                    RANDOM.nextInt(10)
            );
        }

        return result.toString();
    }
}
