package com.pruebatecnica.prueba_tecnica.Account.Domain.Model;

import com.pruebatecnica.prueba_tecnica.shared.Domain.Model.BaseCatalog;

public class ProductStatus extends BaseCatalog {

    public static final String ACTIVE = "ACTIVA";
    public static final String INACTIVE = "INACTIVA";
    public static final String CANCELLED = "CANCELADA";

    public ProductStatus() {
        super();
    }

    public ProductStatus(Long id, String code, String name, boolean active) {
        super(id, code, name, active);
    }
}