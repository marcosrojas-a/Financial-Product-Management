package com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Input;

import com.pruebatecnica.prueba_tecnica.Client.Domain.Model.Client;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.UpdateUseCase;

public interface UpdateClientPort  extends UpdateUseCase<UpdateClientCommand, Client, Long> {
}
