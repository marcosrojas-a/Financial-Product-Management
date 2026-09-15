package com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Adapter;

import com.pruebatecnica.prueba_tecnica.Client.Domain.Model.Client;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Output.ClientRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Entity.ClientEntity;
import com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Mapper.ClientMapper;
import com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Repository.ClientJpaRepository;
import com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Persistence.Adapter.AbstractDeletableCrudRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClientRepositoryAdapter extends AbstractDeletableCrudRepositoryAdapter<Client, ClientEntity, Long>
        implements ClientRepositoryPort {

    private final ClientJpaRepository clientJpaRepository;

    public ClientRepositoryAdapter(ClientJpaRepository clientJpaRepository){
        super(clientJpaRepository, ClientMapper::toDomain , ClientMapper::toEntity);
        this.clientJpaRepository = clientJpaRepository;
    }

    @Override
    public Optional<Client> findByDocumentNumber(String documentNumber) {
        return clientJpaRepository.findByDocumentNumber(documentNumber).map(ClientMapper::toDomain);
    }

}
