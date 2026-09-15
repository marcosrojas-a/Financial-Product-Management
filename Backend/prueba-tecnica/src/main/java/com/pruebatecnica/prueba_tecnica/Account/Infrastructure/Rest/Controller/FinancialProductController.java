package com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Rest.Controller;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.CreateFinancialProductCommand;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.FinancialProductCrudPort;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.FindFinancialProductPort;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input.UpdateProductStatusCommand;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Rest.Dto.Request.FinancialProductRequest;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Rest.Dto.Response.FinancialProductResponse;
import com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Rest.Controller.AbstractCrudController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/productos-financieros")
public class FinancialProductController  extends AbstractCrudController<CreateFinancialProductCommand, UpdateProductStatusCommand, FinancialProduct,
        Long,
        FinancialProductRequest,
        FinancialProductResponse> {

    private final FindFinancialProductPort
            findFinancialProductPort;

    public FinancialProductController(
            FinancialProductCrudPort crudUseCase,
            FindFinancialProductPort findFinancialProductPort) {

        super(
                crudUseCase,
                FinancialProductController::toCreateCommand,
                FinancialProductController::toUpdateCommand,
                FinancialProductResponse::from
        );

        this.findFinancialProductPort =
                findFinancialProductPort;
    }


    // ENDPOINTS DE FINANCIAL PRODUCT


    @GetMapping("/cuenta/{accountNumber}")
    public FinancialProductResponse getByAccountNumber(
            @PathVariable String accountNumber) {

        return FinancialProductResponse.from(
                findFinancialProductPort
                        .getByAccountNumber(accountNumber)
        );
    }

    @GetMapping("/cliente/{clientId}")
    public List<FinancialProductResponse> getByClientId(
            @PathVariable Long clientId) {

        return findFinancialProductPort
                .getByClientId(clientId)
                .stream()
                .map(FinancialProductResponse::from)
                .toList();
    }


    // MAPPERS REQUEST -> COMMAND


    private static CreateFinancialProductCommand
    toCreateCommand(FinancialProductRequest request) {

        return new CreateFinancialProductCommand(
                request.getClientId(),
                request.getProductTypeId(),
                request.getInitialBalance(),
                Boolean.TRUE.equals(
                        request.getExemptGmf()
                )
        );
    }

    private static UpdateProductStatusCommand
    toUpdateCommand(FinancialProductRequest request) {

        return new UpdateProductStatusCommand(
                request.getNewStatusId()
        );
    }

}
