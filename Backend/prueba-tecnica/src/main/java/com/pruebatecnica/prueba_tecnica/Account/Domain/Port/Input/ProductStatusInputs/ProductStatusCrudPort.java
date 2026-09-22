package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductStatusInputs;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductStatus;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Input.CrudUseCase;

public interface ProductStatusCrudPort extends CrudUseCase <CreateProductStatusCommand,UpdateProductStatusCatalogCommand, ProductStatus,Long> {
}
