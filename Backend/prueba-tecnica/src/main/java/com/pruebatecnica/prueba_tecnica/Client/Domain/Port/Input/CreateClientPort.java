package com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input;

import com.pruebatecnica.prueba_tecnica.Client.Domain.Model.Client;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.CreateUseCase;

public interface CreateClientPort extends CreateUseCase<CreateClientCommand, Client> {
}
