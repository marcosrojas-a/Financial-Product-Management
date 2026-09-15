package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductType;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.CrudUseCase;

public interface ProductTypeCrudPort extends CrudUseCase<
        ProductType,
        ProductType,
        ProductType,
        Long> {
}
