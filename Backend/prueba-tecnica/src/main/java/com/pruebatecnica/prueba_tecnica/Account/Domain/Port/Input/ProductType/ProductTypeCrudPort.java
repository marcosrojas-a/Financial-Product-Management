package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductType;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductType;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.CrudUseCase;

public interface ProductTypeCrudPort extends CrudUseCase<CreateProductTypeCommand, UpdateProductTypeCommand, ProductType, Long> {
}
