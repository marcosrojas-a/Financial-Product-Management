package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Adapter;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Transfer;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Output.TransferRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Entity.TransferEntity;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Mapper.TransferMapper;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Repository.TransferJpaRepository;
import com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Persistence.Adapter.AbstractCrudRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransferRepositoryAdapter extends AbstractCrudRepositoryAdapter<Transfer, TransferEntity, Long>
        implements TransferRepositoryPort {

    private final TransferJpaRepository transferJpaRepository;

    public TransferRepositoryAdapter(TransferJpaRepository transferJpaRepository) {
       super(transferJpaRepository, TransferMapper::toDomain, TransferMapper::toEntity);
       this.transferJpaRepository = transferJpaRepository;
    }

    @Override
    public Optional<Transfer> findByOriginMovementId(Long originMovementId) {
        return transferJpaRepository.findByOriginMovementId(originMovementId).map(TransferMapper::toDomain);
    }

}
