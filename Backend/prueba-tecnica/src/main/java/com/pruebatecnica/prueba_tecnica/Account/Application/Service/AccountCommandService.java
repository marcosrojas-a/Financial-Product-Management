package com.pruebatecnica.prueba_tecnica.Account.Application.Service;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductStatus;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductType;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.*;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.ClientExistenceLookupPort;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.FinancialProductRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.ProductStatusRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.ProductTypeRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AccountCommandService  implements CreateFinancialProductPort, UpdateProductStatusPort, CancelFinancialProductPort {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int ACCOUNT_NUMBER_CORRELATIVE_LENGTH = 8;

    private final FinancialProductRepositoryPort financialProductRepositoryPort;
    private final ProductTypeRepositoryPort productTypeRepositoryPort;
    private final ProductStatusRepositoryPort productStatusRepositoryPort;
    private final ClientExistenceLookupPort clientExistenceLookupPort;

    public AccountCommandService(FinancialProductRepositoryPort financialProductRepositoryPort,
                                 ProductTypeRepositoryPort productTypeRepositoryPort,
                                 ProductStatusRepositoryPort productStatusRepositoryPort,
                                 ClientExistenceLookupPort clientExistenceLookupPort) {
        this.financialProductRepositoryPort = financialProductRepositoryPort;
        this.productTypeRepositoryPort = productTypeRepositoryPort;
        this.productStatusRepositoryPort = productStatusRepositoryPort;
        this.clientExistenceLookupPort = clientExistenceLookupPort;
    }

    @Override
    @Transactional
    public FinancialProduct execute(CreateFinancialProductCommand command) {
        validateClientExists(command.getClientId());

        ProductType productType = productTypeRepositoryPort.findById(command.getProductTypeId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "El tipo de producto con id " + command.getProductTypeId() + " no existe."));

        // toda cuenta se crea con estado Activa por defecto
        ProductStatus activeStatus = productStatusRepositoryPort.findByCode(ProductStatus.ACTIVE)
                .orElseThrow(() -> new IllegalStateException(
                        "Catálogo de estados no inicializado: falta " + ProductStatus.ACTIVE));

        // número de cuenta generado automáticamente, único, 10 dígitos, con prefijo por tipo
        String accountNumber = generateUniqueAccountNumber(productType);

        FinancialProduct product = new FinancialProduct(
                command.getClientId(), productType, activeStatus, accountNumber,
                command.getInitialBalance(), command.isExemptGmf());

        return financialProductRepositoryPort.save(product);
    }

    @Override
    @Transactional
    public FinancialProduct execute(Long id, UpdateProductStatusCommand command) {
        FinancialProduct product = getProductOrThrow(id);

        ProductStatus newStatus = productStatusRepositoryPort.findById(command.getNewStatusId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "El estado con id " + command.getNewStatusId() + " no existe."));

        product.setProductStatus(newStatus);
        return financialProductRepositoryPort.save(product);
    }

    @Override
    @Transactional
    public void execute(Long id) {
        FinancialProduct product = getProductOrThrow(id);

        ProductStatus cancelledStatus = productStatusRepositoryPort.findByCode(ProductStatus.CANCELLED)
                .orElseThrow(() -> new IllegalStateException(
                        "Catálogo de estados no inicializado: falta " + ProductStatus.CANCELLED));

        // cancelAccount ya valida internamente que el saldo sea $0
        product.cancelAccount(cancelledStatus);
        financialProductRepositoryPort.save(product);
    }

    private FinancialProduct getProductOrThrow(Long id) {
        return financialProductRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El producto financiero con id " + id + " no existe."));
    }

    private void validateClientExists(Long clientId) {
        if (!clientExistenceLookupPort.existsById(clientId)) {
            throw new IllegalArgumentException("El cliente con id " + clientId + " no existe.");
        }
    }

    private String generateUniqueAccountNumber(ProductType productType) {
        String prefix = productType.getAccountNumberPrefix();
        String accountNumber;
        do {
            accountNumber = prefix + randomDigits(ACCOUNT_NUMBER_CORRELATIVE_LENGTH);
        } while (financialProductRepositoryPort.findByAccountNumber(accountNumber).isPresent());
        return accountNumber;
    }

    private String randomDigits(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(RANDOM.nextInt(10));
        }
        return builder.toString();
    }
}
