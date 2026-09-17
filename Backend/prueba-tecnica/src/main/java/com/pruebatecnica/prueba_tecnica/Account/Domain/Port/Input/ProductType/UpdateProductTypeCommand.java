package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductType;

public class UpdateProductTypeCommand {

    private final String code;
    private final String name;
    private final boolean active;

    public UpdateProductTypeCommand(
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
