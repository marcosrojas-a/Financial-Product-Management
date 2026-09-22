package com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Rest.Controller;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductType;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductTypeInputs.CreateProductTypeCommand;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductTypeInputs.ProductTypeCrudPort;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.ProductTypeInputs.UpdateProductTypeCommand;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Rest.Dto.Request.ProductTypeRequest;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Rest.Dto.Response.ProductTypeResponse;
import com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Rest.Controller.AbstractCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product-type")
public class ProductTypeController extends AbstractCrudController<CreateProductTypeCommand, UpdateProductTypeCommand, ProductType, Long, ProductTypeRequest, ProductTypeResponse> {

    public ProductTypeController(ProductTypeCrudPort productTypeCrudPort) {

        super(
                productTypeCrudPort,

                request -> new CreateProductTypeCommand(
                        request.getCode(),
                        request.getName(),
                        request.isActive()
                ),

                request -> new UpdateProductTypeCommand(
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
