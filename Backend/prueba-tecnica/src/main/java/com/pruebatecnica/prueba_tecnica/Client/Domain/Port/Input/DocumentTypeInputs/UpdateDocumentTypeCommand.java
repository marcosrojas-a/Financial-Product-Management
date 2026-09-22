package com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.DocumentTypeInputs;

public class UpdateDocumentTypeCommand {
    private final String code;
    private final String name;
    private final boolean active;

    public UpdateDocumentTypeCommand(
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
