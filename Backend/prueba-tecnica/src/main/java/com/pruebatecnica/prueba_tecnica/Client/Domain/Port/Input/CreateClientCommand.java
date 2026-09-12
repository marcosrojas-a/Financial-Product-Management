package com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input;

import java.time.LocalDate;

public class CreateClientCommand {
    private final Long documentTypeId;
    private final String documentNumber;
    private final String names;
    private final String lastNames;
    private final String email;
    private final LocalDate birthDate;

    public CreateClientCommand(Long documentTypeId, String documentNumber, String names,
                               String lastNames, String email, LocalDate birthDate) {
        this.documentTypeId = documentTypeId;
        this.documentNumber = documentNumber;
        this.names = names;
        this.lastNames = lastNames;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Long getDocumentTypeId() { return documentTypeId; }
    public String getDocumentNumber() { return documentNumber; }
    public String getNames() { return names; }
    public String getLastNames() { return lastNames; }
    public String getEmail() { return email; }
    public LocalDate getBirthDate() { return birthDate; }
}
