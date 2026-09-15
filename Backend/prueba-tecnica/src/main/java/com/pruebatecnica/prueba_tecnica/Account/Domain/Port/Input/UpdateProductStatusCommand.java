package com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Input;

public class UpdateProductStatusCommand {
    private final Long newStatusId;

    public UpdateProductStatusCommand(Long newStatusId) {

        this.newStatusId = newStatusId;
    }

    public Long getNewStatusId() {
        return newStatusId;
    }
}
