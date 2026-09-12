package com.pruebatecnica.prueba_tecnica.shared.Domain.Model;
import java.util.Objects;

public class BaseCatalog extends AuditableEntity {

    private String code;
    private String name;
    private boolean active;

    protected BaseCatalog() {
    }

    protected BaseCatalog(Long id, String code, String name, boolean active) {
        super(id);
        this.code = code;
        this.name = name;
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
