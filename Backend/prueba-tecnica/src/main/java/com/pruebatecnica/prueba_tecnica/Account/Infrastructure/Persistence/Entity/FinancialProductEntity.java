package com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "prodicto_financiero")
public class FinancialProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long clientId;

    @Column(name = "tipo_producto_id", nullable = false)
    private Long productTypeId;

    @Column(name = "estado_producto_id", nullable = false)
    private Long productStatusId;

    @Column(name = "numero_cuenta", nullable = false, unique = true, length = 10)
    private String accountNumber;

    @Column(name = "saldo", nullable = false, precision = 18, scale = 2)
    private BigDecimal balance;

    @Column(name = "saldo_disponible", nullable = false, precision = 18, scale = 2)
    private BigDecimal availableBalance;

    @Column(name = "exenta_gmf", nullable = false)
    private boolean exemptGmf;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "fecha_modificacion")
    private LocalDateTime modificationDate;

    protected  FinancialProductEntity(){}

    public FinancialProductEntity(Long id, Long clientId, Long productTypeId, Long productStatusId, String accountNumber, BigDecimal balance, BigDecimal availableBalance,boolean exemptGmf, LocalDateTime creationDate, LocalDateTime modificationDate) {
        this.id = id;
        this.clientId = clientId;
        this.productTypeId = productTypeId;
        this.productStatusId = productStatusId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.availableBalance = availableBalance;
        this.exemptGmf = exemptGmf;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public Long getId() { return id; }
    public Long getClientId() { return clientId; }
    public Long getProductTypeId() { return productTypeId; }
    public Long getProductStatusId() { return productStatusId; }
    public String getAccountNumber() { return accountNumber; }
    public BigDecimal getBalance() { return balance; }
    public BigDecimal getAvailableBalance() { return availableBalance; }
    public boolean isExemptGmf() { return exemptGmf; }
    public LocalDateTime getCreationDate() { return creationDate; }
    public LocalDateTime getModificationDate() { return modificationDate; }
}
