package com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Output;

// Se usa para evitar la eliminación de un cliente
// si aún tiene algún producto financiero vinculado.

public interface ClientProductsLookupPort {
    boolean hasProducts(Long clientId);
}
