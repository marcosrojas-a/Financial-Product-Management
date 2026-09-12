package com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input;

import com.pruebatecnica.prueba_tecnica.Client.Domain.Model.Client;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.FindUseCase;

public interface FindClientPort extends FindUseCase <Client, Long> {
    //Buscar por documento
    Client getByDocument(String documentNumber);
}
