package com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input.Client;

import com.pruebatecnica.prueba_tecnica.Client.Domain.Model.Client;

public interface FindClientByDocumentPort {
    Client getByDocument(String documentNumber);
}
