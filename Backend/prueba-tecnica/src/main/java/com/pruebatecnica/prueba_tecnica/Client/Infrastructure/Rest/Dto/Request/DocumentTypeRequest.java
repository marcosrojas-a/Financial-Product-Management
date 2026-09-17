package com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Rest.Dto.Request;

public class DocumentTypeRequest {
    private String code;
    private String name;
    private boolean active;

    public DocumentTypeRequest() {
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
