package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Dto.Request;

public class MovementTypeRequest {
    private String code;
    private String name;
    private boolean active;

    public MovementTypeRequest() {}

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
