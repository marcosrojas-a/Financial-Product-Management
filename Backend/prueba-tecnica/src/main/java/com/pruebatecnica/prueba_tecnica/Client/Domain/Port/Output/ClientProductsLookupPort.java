package com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Output;

// Se usa para cumplir con el requerimiento de evitar la eliminacion
// de un usuario si aun tiene algun producto vinculado
public interface ClientProductsLookupPort {
    boolean hasActiveProducts(Long clientId);
}
