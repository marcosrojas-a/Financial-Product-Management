export function formatCurrency(amount: number | null | undefined): string {
  if (amount === null || amount === undefined || isNaN(amount)) return '-';
  return new Intl.NumberFormat('es-CO', {
    style: 'currency',
    currency: 'COP',
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  }).format(amount);
}

export function formatDocumentType(code: string): string {
  const types: Record<string, string> = {
    CC: 'Cédula de Ciudadanía',
    CE: 'Cédula de Extranjería',
    PA: 'Pasaporte',
  };
  return types[code] || code;
}

export function isNullOrUndefined(value: unknown): value is null | undefined {
  return value === null || value === undefined;
}
