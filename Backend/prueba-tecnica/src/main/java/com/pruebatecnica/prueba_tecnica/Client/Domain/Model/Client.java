package com.pruebatecnica.prueba_tecnica.Client.Domain.Model;

import com.pruebatecnica.prueba_tecnica.shared.Domain.Model.AuditableEntity;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class Client extends AuditableEntity
{
    private Long documentTypeId;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;

    // Constructor vacío
    protected Client() {
        super();
    }

    // Constructor que centraliza la creación y las reglas de negocio
    public Client(Long documentTypeId, String documentNumber, String firstName, String lastName, String email, LocalDate dateOfBirth) {
        super();
        validateAge(dateOfBirth);
        validateNameLength(firstName, lastName);
        validateEmail(email);

        this.documentTypeId = documentTypeId;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    // REGLAS DE NEGOCIO

    private void validateAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null || Period.between(dateOfBirth, LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException("Cliente no puede ser creado, es menor de edad.");
        }
    }

    private void validateNameLength(String firstName, String lastName) {
        if (firstName == null || firstName.trim().length() < 2 || lastName == null || lastName.trim().length() < 2) {
            throw new IllegalArgumentException("La extensión del nombre y el apellido no puede ser menor a 2 caracteres.");
        }
    }

    private void validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (email == null || !Pattern.matches(emailRegex, email)) {
            throw new IllegalArgumentException("El formato del correo electrónico es inválido.");
        }
    }


    public Long getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Long documentTypeId){
        this.documentTypeId = documentTypeId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
