package com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cliente")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_documento_id", nullable = false)
    private Long documentTypeId;

    @Column(name = "numero_documento", nullable = false, unique = true, length = 20)
    private String documentNumber;

    @Column(name = "nombres", nullable = false, length = 100)
    private String firstName;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String lastName;

    @Column(name = "correo", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "fecha_modificacion")
    private LocalDateTime modificationDate;

    protected ClientEntity() {}

    public ClientEntity(Long id, Long documentTypeId, String documentNumber, String firstName,
                        String lastName, String email, LocalDate dateOfBirth,
                        LocalDateTime creationDate, LocalDateTime modificationDate) {
        this.id = id;
        this.documentTypeId = documentTypeId;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modificationDate = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Long getDocumentTypeId() { return documentTypeId; }
    public String getDocumentNumber() { return documentNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public LocalDateTime getCreationDate() { return creationDate; }
    public LocalDateTime getModificationDate() { return modificationDate; }
}


