package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output;

public interface ClientExistenceLookupPort {
    boolean existsById(Long clientId);
}
