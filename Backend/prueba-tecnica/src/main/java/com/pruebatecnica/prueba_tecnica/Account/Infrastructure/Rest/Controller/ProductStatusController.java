package com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Rest.Controller;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductStatus;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductStatusInputs.CreateProductStatusCommand;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductStatusInputs.ProductStatusCrudPort;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductStatusInputs.UpdateProductStatusCatalogCommand;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Rest.Dto.Request.ProductStatusRequest;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Rest.Dto.Response.ProductStatusResponse;
import com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Rest.Controller.AbstractCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product-status")
public class ProductStatusController extends AbstractCrudController<CreateProductStatusCommand, UpdateProductStatusCatalogCommand,
        ProductStatus, Long, ProductStatusRequest, ProductStatusResponse> {

    public ProductStatusController(ProductStatusCrudPort productStatusCrudPort) {
        super(
                productStatusCrudPort,

                request -> new CreateProductStatusCommand(
                        request.getCode(), request.getName(), request.isActive()),

                request -> new UpdateProductStatusCatalogCommand(
                        request.getCode(), request.getName(), request.isActive()),

                productStatus -> new ProductStatusResponse(
                        productStatus.getId(), productStatus.getCode(),
                        productStatus.getName(), productStatus.isActive())
        );
    }
}
