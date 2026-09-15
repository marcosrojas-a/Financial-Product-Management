package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_movimiento")
public class MovementTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String codigo;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private boolean activo;


    protected MovementTypeEntity() {}

    public MovementTypeEntity(Long id, String codigo, String nombre, boolean activo) {
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
