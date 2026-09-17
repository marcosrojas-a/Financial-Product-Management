package com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Output;

import com.pruebatecnica.prueba_tecnica.shared.Domain.Model.BaseCatalog;

import java.util.List;
import java.util.Optional;

public interface CatalogRepositoryPort <T extends BaseCatalog> {

    T save(T entity);
    Optional<T> findById(Long id);
    Optional<T> findByCode(String code);
    List<T> findAll();
}

