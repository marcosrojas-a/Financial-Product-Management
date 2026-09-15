package com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_documento")
public class DocumentTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String codigo;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private boolean activo;

    protected DocumentTypeEntity() {}

    public DocumentTypeEntity(Long id, String codigo, String nombre, boolean activo) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.activo = activo;
    }

    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public boolean isActivo() { return activo; }

}
