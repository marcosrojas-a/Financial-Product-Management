package com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Rest.Controller;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductType;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductTypeCrudPort;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Rest.Dto.Request.ProductTypeRequest;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Rest.Dto.Response.ProductTypeResponse;
import com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Rest.Controller.AbstractCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product-types")
public class ProductTypeController extends AbstractCrudController<ProductType, ProductType, ProductType, Long, ProductTypeRequest, ProductTypeResponse> {

    public ProductTypeController(ProductTypeCrudPort productTypeCrudPort) {

        super(
                productTypeCrudPort,

                request -> new ProductType(
                        null,
                        request.getCode(),
                        request.getName(),
                        request.isActive()
                ),

                request -> new ProductType(
                        null,
                        request.getCode(),
                        request.getName(),
                        request.isActive()
                ),

                productType -> new ProductTypeResponse(
                        productType.getId(),
                        productType.getCode(),
                        productType.getName(),
                        productType.isActive()
                )
        );
    }
}
