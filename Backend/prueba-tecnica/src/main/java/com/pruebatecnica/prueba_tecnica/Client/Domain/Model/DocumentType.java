package com.pruebatecnica.prueba_tecnica.Client.Domain.Model;

import com.pruebatecnica.prueba_tecnica.shared.Domain.Model.BaseCatalog;

public class DocumentType extends BaseCatalog {

    public DocumentType() {
        super();
    }

    public DocumentType(Long id, String code, String name, boolean active) {
        super(id, code, name, active);
    }
}
