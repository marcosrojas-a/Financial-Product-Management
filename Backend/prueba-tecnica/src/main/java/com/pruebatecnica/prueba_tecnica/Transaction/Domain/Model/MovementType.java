package com.pruebatecnica.prueba_tecnica.Transaction.Domain.Model;

import com.pruebatecnica.prueba_tecnica.shared.Domain.Model.BaseCatalog;

public class MovementType extends BaseCatalog {
    // Constantes de Dominio para los 3 tipos permitidos
    public static final String CONSIGNMENT = "CONSIGNACION";
    public static final String WITHDRAWAL = "RETIRO";
    public static final String TRANSFER = "TRANSFERENCIA";

    public MovementType() {
        super();
    }

    public MovementType(Long id, String code, String name, boolean active) {
        super(id, code, name, active);
    }

    //Métodos para identificar el tipo
    public boolean isConsignment() {
        return CONSIGNMENT.equalsIgnoreCase(getCode());
    }

    public boolean isWithdrawal() {
        return WITHDRAWAL.equalsIgnoreCase(getCode());
    }

    public boolean isTransfer() {
        return TRANSFER.equalsIgnoreCase(getCode());
    }

    // Valida que el código corresponda a una transacción permitida
    public static void validateAllowedCode(String code) {
        if (code == null || (!CONSIGNMENT.equalsIgnoreCase(code)
                && !WITHDRAWAL.equalsIgnoreCase(code)
                && !TRANSFER.equalsIgnoreCase(code))) {
            throw new IllegalArgumentException("El tipo de transacción '" + code + "' no es permitido.");
        }
    }
}
