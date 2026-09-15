package com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Rest.Dto.Request;

public class ProductTypeRequest {
    private String code;
    private String name;
    private boolean active;

    public ProductTypeRequest() {
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }
}
