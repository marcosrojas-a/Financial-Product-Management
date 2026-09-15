package com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Adapter;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Transfer;
import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Output.TransferRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Mapper.TransferMapper;
import com.pruebatecnica.prueba_tecnica.Transaction.Infrastructure.Persistence.Repository.TransferJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TransferRepositoryAdapter implements TransferRepositoryPort {

    private final TransferJpaRepository transferJpaRepository;

    public TransferRepositoryAdapter(TransferJpaRepository transferJpaRepository) {
        this.transferJpaRepository = transferJpaRepository;
    }

    @Override
    public Transfer save(Transfer transfer) {
        return TransferMapper.toDomain(transferJpaRepository.save(TransferMapper.toEntity(transfer)));
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        return transferJpaRepository.findById(id).map(TransferMapper::toDomain);
    }

    @Override
    public List<Transfer> findAll() {
        return transferJpaRepository.findAll().stream()
                .map(TransferMapper::toDomain)
                .toList();
    }
}
