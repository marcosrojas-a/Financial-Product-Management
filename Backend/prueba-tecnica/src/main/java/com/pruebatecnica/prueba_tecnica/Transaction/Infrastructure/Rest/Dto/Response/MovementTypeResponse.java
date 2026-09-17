package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Rest.Dto.Response;

public class MovementTypeResponse {
    private Long id;
    private String code;
    private String name;
    private boolean active;

    public MovementTypeResponse(Long id, String code, String name, boolean active) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.active = active;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public boolean isActive() { return active; }
}
