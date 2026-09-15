package com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Repository;

import com.pruebatecnica.prueba_tecnica.Account.Infrastructure.Persistence.Entity.FinancialProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FinancialProductJpaRepository extends JpaRepository<FinancialProductEntity, Long> {
    Optional<FinancialProductEntity> findByAccountNumber(String accountNumber);

    List<FinancialProductEntity> findByClientId(Long clientId);

    @Query("select count(p) > 0 from FinancialProductEntity p " +
            "where p.clientId = :clientId and p.productStatusId <> :cancelledStatusId")
    boolean existsActiveByClientId(@Param("clientId") Long clientId,
                                   @Param("cancelledStatusId") Long cancelledStatusId);
}
