package com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Rest.Dto.Response;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;

import java.math.BigDecimal;

public class FinancialProductResponse {
    private Long id;
    private Long clientId;

    private Long productTypeId;
    private String productType;

    private Long productStatusId;
    private String productStatus;

    private String accountNumber;

    private BigDecimal balance;
    private BigDecimal availableBalance;

    private boolean exemptGmf;

    public FinancialProductResponse() {
    }

    public static FinancialProductResponse from(
            FinancialProduct product) {

        FinancialProductResponse response =
                new FinancialProductResponse();

        response.id = product.getId();
        response.clientId = product.getClientId();
        response.productTypeId =
                product.getProductType().getId();
        response.productType =
                product.getProductType().getName();
        response.productStatusId =
                product.getProductStatus().getId();
        response.productStatus =
                product.getProductStatus().getName();
        response.accountNumber =
                product.getAccountNumber();
        response.balance =
                product.getBalance();
        response.availableBalance =
                product.getAvailableBalance();
        response.exemptGmf =
                product.isExemptGmf();

        return response;
    }

    public Long getId() {
        return id;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public String getProductType() {
        return productType;
    }

    public Long getProductStatusId() {
        return productStatusId;
    }

    public String getProductStatus() {
        return productStatus;
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
}
