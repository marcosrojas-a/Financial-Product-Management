package com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Mapper;

import com.pruebatecnica.prueba_tecnica.Client.Domain.Model.Client;
import com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Entity.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public static Client toDomain(ClientEntity entity){
        if(entity == null)
            return null;
        Client client = new Client(entity.getDocumentTypeId(), entity.getDocumentNumber(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getDateOfBirth());
        client.setId(entity.getId());
        client.setCreationDate(entity.getCreationDate());
        client.setModificationDate(entity.getModificationDate());
        return client;
    }

    public static ClientEntity toEntity(Client domain){
        if (domain == null)
            return null;
        return new ClientEntity(domain.getId(), domain.getDocumentTypeId(), domain.getDocumentNumber(),  domain.getFirstName(), domain.getLastName(), domain.getEmail(), domain.getDateOfBirth(),domain.getCreationDate(), domain.getModificationDate());
    }
}
