package com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Adapter;

import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.FinancialProduct;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Model.ProductStatus;
import com.pruebatecnica.prueba_tecnica.Account.Domain.Port.Output.FinancialProductRepositoryPort;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Mapper.FinancialProductMapper;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Repository.FinancialProductJpaRepository;
import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Repository.ProductStatusJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FinancialProductRepositoryAdapter implements FinancialProductRepositoryPort {
    private final FinancialProductJpaRepository financialProductJpaRepository;
    private final ProductStatusJpaRepository productStatusJpaRepository;
    private final FinancialProductMapper financialProductMapper;

    public  FinancialProductRepositoryAdapter (FinancialProductJpaRepository financialProductJpaRepository,
                                               ProductStatusJpaRepository productStatusJpaRepository,
                                               FinancialProductMapper financialProductMapper){
        this.financialProductJpaRepository = financialProductJpaRepository;
        this.productStatusJpaRepository = productStatusJpaRepository;
        this.financialProductMapper = financialProductMapper;

    }

    @Override
    public FinancialProduct save(FinancialProduct product){
        var saved = financialProductJpaRepository.save(financialProductMapper.toEntity(product));
        return financialProductMapper.toDomain(saved);
    }

    @Override
    public Optional<FinancialProduct> findById(Long id){
        return financialProductJpaRepository.findById(id).map(financialProductMapper::toDomain);
    }

    @Override
    public List<FinancialProduct> findByClientId(Long clientId){
        return financialProductJpaRepository.findByClientId(clientId).stream().map(financialProductMapper::toDomain).toList();
    }
    @Override
    public Optional<FinancialProduct> findByAccountNumber(String accountNumber) {
        return financialProductJpaRepository.findByAccountNumber(accountNumber).map(financialProductMapper::toDomain);
    }

    @Override
    public List<FinancialProduct> findAll() {
        return financialProductJpaRepository.findAll().stream().map(financialProductMapper::toDomain).toList();
    }

    @Override
    public boolean existsActiveByClientId(Long clientId) {
        Long cancelledStatusId = productStatusJpaRepository.findByCodigo(ProductStatus.CANCELLED)
                .orElseThrow(() -> new IllegalStateException("Catálogo de estados no inicializado: falta CANCELADA"))
                .getId();
        return financialProductJpaRepository.existsActiveByClientId(clientId, cancelledStatusId);
    }

}
