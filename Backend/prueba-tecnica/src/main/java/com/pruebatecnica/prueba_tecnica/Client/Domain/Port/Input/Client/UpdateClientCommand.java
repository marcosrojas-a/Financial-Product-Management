package com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.Client;

public class UpdateClientCommand {
    private final Long documentTypeId;
    private final String documentNumber;
    private final String names;
    private final String lastNames;
    private final String email;

    public UpdateClientCommand(Long documentTypeId, String documentNumber, String names,
                               String lastNames, String email) {
        this.documentTypeId = documentTypeId;
        this.documentNumber = documentNumber;
        this.names = names;
        this.lastNames = lastNames;
        this.email = email;
    }

    public Long getDocumentTypeId() { return documentTypeId; }
    public String getDocumentNumber() { return documentNumber; }
    public String getNames() { return names; }
    public String getLastNames() { return lastNames; }
    public String getEmail() { return email; }
}
