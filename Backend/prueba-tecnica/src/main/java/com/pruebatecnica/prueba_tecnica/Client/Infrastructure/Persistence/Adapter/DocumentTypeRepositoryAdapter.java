package com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Adapter;

import com.pruebatecnica.prueba_tecnica.Client.Domain.Model.DocumentType;
import com.pruebatecnica.prueba_tecnica.Client.Domain.Port.Output.DocumentTypeRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Entity.DocumentTypeEntity;
import com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Mapper.DocumentTypeMapper;
import com.pruebatecnica.prueba_tecnica.Client.Infrastructure.Persistence.Repository.DocumentTypeJpaRepository;
import com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Persistence.Adapter.AbstractCrudRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DocumentTypeRepositoryAdapter  extends AbstractCrudRepositoryAdapter<DocumentType, DocumentTypeEntity, Long>
        implements DocumentTypeRepositoryPort {

    private final DocumentTypeJpaRepository documentTypeJpaRepository;

    public DocumentTypeRepositoryAdapter(DocumentTypeJpaRepository documentTypeJpaRepository) {
        super(documentTypeJpaRepository, DocumentTypeMapper::toDomain, DocumentTypeMapper::toEntity);
        this.documentTypeJpaRepository = documentTypeJpaRepository;
    }

    @Override
    public Optional<DocumentType> findByCode(String code) {
        return documentTypeJpaRepository.findByCodigo(code).map(DocumentTypeMapper::toDomain);
    }
}

