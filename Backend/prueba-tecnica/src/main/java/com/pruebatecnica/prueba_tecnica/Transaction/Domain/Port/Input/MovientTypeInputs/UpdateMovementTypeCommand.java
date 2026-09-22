package com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Input.MovientTypeInputs;

public class UpdateMovementTypeCommand {
    private final String code;
    private final String name;
    private final boolean active;

    public UpdateMovementTypeCommand(String code, String name, boolean active) {
        this.code = code;
        this.name = name;
        this.active = active;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public boolean isActive() { return active; }
}
