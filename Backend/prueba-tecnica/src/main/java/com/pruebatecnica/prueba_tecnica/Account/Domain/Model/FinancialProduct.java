package com.pruebatecnica.prueba_tecnica.Account.Domain.Model;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Movement;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.MovementType;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Model.AuditableEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FinancialProduct extends AuditableEntity {

    private Long clientId;
    private ProductType productType;
    private ProductStatus productStatus;
    private String accountNumber;
    private BigDecimal balance;
    private BigDecimal availableBalance;
    private boolean exemptGmf;

    protected FinancialProduct() {
        super();
    }

    public FinancialProduct(Long clientId, ProductType productType, ProductStatus productStatus, String accountNumber, BigDecimal initialBalance, boolean exemptGmf) {
        super();

        if (clientId == null) {
            throw new IllegalArgumentException("Todo producto financiero debe estar vinculado a un cliente.");
        }
        if (productType == null) {
            throw new IllegalArgumentException("Debe especificar un tipo de producto válido.");
        }

        // Variables para las validaciones al objeto ProductType
        productType.validateAccountNumber(accountNumber);
        productType.validateBalance(initialBalance);

        this.clientId = clientId;
        this.productType = productType;
        this.productStatus = productStatus;
        this.accountNumber = accountNumber;
        this.balance = (initialBalance != null) ? initialBalance : BigDecimal.ZERO;
        this.availableBalance = this.balance;
        this.exemptGmf = exemptGmf;
    }

    //Regla de cancelación
    public void cancelAccount(ProductStatus cancelledStatus) {
        if (this.balance != null && this.balance.compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalStateException("Únicamente se pueden cancelar cuentas con un saldo igual a $0.");
        }
        this.productStatus = cancelledStatus;
    }

    // Manejo de Movimiento entre cuentas

    public Movement debit(BigDecimal amount, MovementType movementType, String description) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a debitar debe ser mayor a cero.");
        }

        // Validar saldo suficiente sobre el estado actual del producto
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Saldo insuficiente. Saldo disponible: $" + this.balance);
        }

        BigDecimal previousBalance = this.balance;
        BigDecimal newBalance = this.balance.subtract(amount);

        // Validar regla de tipo de producto (ej. Ahorros no < $0)
        if (this.productType != null) {
            this.productType.validateBalance(newBalance);
        }

        // Modificar el estado de la cuenta
        this.balance = newBalance;
        this.availableBalance = this.availableBalance.subtract(amount);

        // Retornar el recibo/histórico de la operación
        return new Movement(
                this.getId(),
                movementType,
                amount,
                previousBalance,
                newBalance,
                description,
                LocalDateTime.now()
        );
    }


    public Movement credit(BigDecimal amount, MovementType movementType, String description) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a consignar debe ser mayor a cero.");
        }

        BigDecimal previousBalance = this.balance;
        BigDecimal newBalance = this.balance.add(amount);

        this.balance = newBalance;
        this.availableBalance = this.availableBalance.add(amount);

        return new Movement(
                this.getId(),
                movementType,
                amount,
                previousBalance,
                newBalance,
                description,
                LocalDateTime.now()
        );
    }

    // Getters y Setters
    public Long getClientId() {
        return clientId;
    }
    public ProductType getProductType() {
        return productType;
    }
    public ProductStatus getProductStatus() {
        return productStatus;
    }
    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }
    public boolean isExemptGmf() {
        return exemptGmf;
    }
    public void setExemptGmf(boolean exemptGmf) {
        this.exemptGmf = exemptGmf;
    }
}