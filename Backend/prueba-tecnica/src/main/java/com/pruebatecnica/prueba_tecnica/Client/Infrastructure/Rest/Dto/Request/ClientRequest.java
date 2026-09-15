package com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Rest.Dto.Request;

import java.time.LocalDate;

public class ClientRequest {

    private Long tipoDocumentoId;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private String correo;
    private LocalDate fechaNacimiento;

    public Long getTipoDocumentoId() {
        return tipoDocumentoId;
    }
    public void setTipoDocumentoId(Long tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }
    public String getNumeroDocumento() {
        return numeroDocumento;
    }
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
