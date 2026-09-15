package com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Rest.Dto.Response;

import com.pruebatecnica.prueba_tecnica.Client.Domain.Model.Client;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClientResponse {
    private Long id;
    private Long tipoDocumentoId;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private String correo;
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

    public static ClientResponse from(Client client) {
        ClientResponse response = new ClientResponse();
        response.id = client.getId();
        response.tipoDocumentoId = client.getDocumentTypeId();
        response.numeroDocumento = client.getDocumentNumber();
        response.nombres = client.getFirstName();
        response.apellidos = client.getLastName();
        response.correo = client.getEmail();
        response.fechaNacimiento = client.getDateOfBirth();
        response.fechaCreacion = client.getCreationDate();
        response.fechaModificacion = client.getModificationDate();
        return response;
    }

    public Long getId() {
        return id;
    }
    public Long getTipoDocumentoId() {
        return tipoDocumentoId;
    }
    public String getNumeroDocumento() {
        return numeroDocumento;
    }
    public String getNombres() {
        return nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getCorreo() {
        return correo;
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }
}
