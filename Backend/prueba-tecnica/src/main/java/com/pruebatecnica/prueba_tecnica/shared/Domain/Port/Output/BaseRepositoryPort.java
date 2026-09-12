package com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Output;

import java.util.List;
import java.util.Optional;

public interface BaseRepositoryPort<ENTITY, ID> extends ReadRepositoryPort<ENTITY, ID>, WriteRepositoryPort<ENTITY>, DeleteRepositoryPort<ID> {

}
