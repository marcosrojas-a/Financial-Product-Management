package com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Adapter;

import com.pruebatecnica.prueba_tecnica.Client.Domain.Model.Client;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Output.ClientRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Mapper.ClientMapper;
import com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Repository.ClientJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClientRepositoryAdapter implements ClientRepositoryPort {

    private final ClientJpaRepository clientJpaRepository;

    public ClientRepositoryAdapter(ClientJpaRepository clientJpaRepository){
        this.clientJpaRepository = clientJpaRepository;
    }

    @Override
    public Client save(Client client){
        return ClientMapper.toDomain(clientJpaRepository.save(ClientMapper.toEntity((client))));
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientJpaRepository.findById(id).map(ClientMapper::toDomain);
    }

    @Override
    public Optional<Client> findByDocumentNumber(String documentNumber) {
        return clientJpaRepository.findByDocumentNumber(documentNumber).map(ClientMapper::toDomain);
    }

    @Override
    public List<Client> findAll() {
        return clientJpaRepository.findAll().stream().map(ClientMapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        clientJpaRepository.deleteById(id);
    }
}
