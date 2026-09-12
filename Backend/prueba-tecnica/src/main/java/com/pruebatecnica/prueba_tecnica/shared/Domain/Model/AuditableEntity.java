package com.pruebatecnica.prueba_tecnica.shared.Domain.Model;
import java.time.LocalDateTime;


public class AuditableEntity extends BaseEntity {
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

    protected AuditableEntity() {
        super();
    }

    protected AuditableEntity(Long id) {
        super(id);
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }
}
