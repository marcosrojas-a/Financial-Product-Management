package com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Persistence.Adapter;

import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Output.ReadRepositoryPort;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Output.WriteRepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class AbstractCrudRepositoryAdapter <DOMAIN, ENTITY, ID>
        implements ReadRepositoryPort<DOMAIN, ID>, WriteRepositoryPort<DOMAIN> {

    protected final JpaRepository<ENTITY, ID> jpaRepository;
    private final Function<ENTITY, DOMAIN> toDomain;
    private final Function<DOMAIN, ENTITY> toEntity;

    protected AbstractCrudRepositoryAdapter(JpaRepository<ENTITY, ID> jpaRepository,
                                            Function<ENTITY, DOMAIN> toDomain,
                                            Function<DOMAIN, ENTITY> toEntity) {
        this.jpaRepository = jpaRepository;
        this.toDomain = toDomain;
        this.toEntity = toEntity;
    }

    @Override
    public DOMAIN save(DOMAIN domain) {
        return toDomain.apply(jpaRepository.save(toEntity.apply(domain)));
    }

    @Override
    public Optional<DOMAIN> findById(ID id) {
        return jpaRepository.findById(id).map(toDomain);
    }

    @Override
    public List<DOMAIN> findAll() {
        return jpaRepository.findAll().stream().map(toDomain).toList();
    }
}
