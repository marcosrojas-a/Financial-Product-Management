package com.pruebatecnica.prueba_tecnica.Transaction.Domain.Port.Output;

import com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model.Transfer;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Output.ReadRepositoryPort;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Port.Output.WriteRepositoryPort;

public interface TransferRepositoryPort extends ReadRepositoryPort<Transfer, Long>, WriteRepositoryPort<Transfer> {
}

