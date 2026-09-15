package com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Persistence.Adapter;

import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Output.DeleteRepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.function.Function;

public abstract class AbstractDeletableCrudRepositoryAdapter <DOMAIN, ENTITY, ID>
        extends AbstractCrudRepositoryAdapter<DOMAIN, ENTITY, ID>
        implements DeleteRepositoryPort<ID> {

    protected AbstractDeletableCrudRepositoryAdapter(JpaRepository<ENTITY, ID> jpaRepository,
                                                     Function<ENTITY, DOMAIN> toDomain,
                                                     Function<DOMAIN, ENTITY> toEntity) {
        super(jpaRepository, toDomain, toEntity);
    }

    @Override
    public void deleteById(ID id) {
        jpaRepository.deleteById(id);
    }
}
