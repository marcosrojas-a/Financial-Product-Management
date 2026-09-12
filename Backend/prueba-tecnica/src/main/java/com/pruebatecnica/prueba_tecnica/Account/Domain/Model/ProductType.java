package com.pruebatecnica.prueba_tecnica.Account.Domain.Model;
import com.pruebatecnica.prueba_tecnica.shared.Domain.Model.BaseCatalog;
import java.math.BigDecimal;

public class ProductType extends BaseCatalog {

    public static final String SAVINGS_CODE = "AHORROS";
    public static final String CHECKING_CODE = "CORRIENTE";

    public ProductType() {
        super();
    }

    public ProductType(Long id, String code, String name, boolean active) {
        super(id, code, name, active);
    }

    public boolean isSavings() {
        return SAVINGS_CODE.equalsIgnoreCase(getCode());
    }

    public boolean isChecking() {
        return CHECKING_CODE.equalsIgnoreCase(getCode());
    }

    public String getAccountNumberPrefix() {
        if (isSavings()) return "53";
        if (isChecking()) return "33";
        throw new IllegalStateException("Tipo de producto no soportado: " + getCode());
    }

    // Longitud de 10 dígitos y prefijo por tipo de cuenta
    public void validateAccountNumber(String accountNumber) {
        if (accountNumber == null || !accountNumber.matches("^\\d{10}$")) {
            throw new IllegalArgumentException("El número de cuenta debe tener exactamente 10 dígitos numéricos.");
        }
        String prefix = getAccountNumberPrefix();
        if (!accountNumber.startsWith(prefix)) {
            throw new IllegalArgumentException("La cuenta de tipo " + getName() + " debe iniciar con el prefijo '" + prefix + "'.");
        }
    }

    // Saldo no negativo para cuentas de ahorro
    public void validateBalance(BigDecimal balance) {
        if (isSavings() && balance != null && balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Una cuenta de ahorros no puede tener un saldo inferior a $0.");
        }
    }
}


