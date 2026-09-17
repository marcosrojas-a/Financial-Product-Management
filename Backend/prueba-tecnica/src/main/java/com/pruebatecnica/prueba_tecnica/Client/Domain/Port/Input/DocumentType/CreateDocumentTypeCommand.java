package com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.DocumentType;

public class CreateDocumentTypeCommand {

    private final String code;
    private final String name;
    private final boolean active;

    public CreateDocumentTypeCommand(
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
