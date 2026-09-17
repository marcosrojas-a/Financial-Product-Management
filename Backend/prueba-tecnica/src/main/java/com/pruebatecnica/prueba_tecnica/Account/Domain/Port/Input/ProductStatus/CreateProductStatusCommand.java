package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductStatus;

public class CreateProductStatusCommand {
    private final String code;
    private final String name;
    private final boolean active;

    public CreateProductStatusCommand(
            String code,
            String name,
            boolean active) {

        this.code = code;
        this.name = name;
        this.active = active;
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
