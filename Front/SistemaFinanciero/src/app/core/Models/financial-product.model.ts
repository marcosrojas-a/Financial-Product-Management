export interface ProductType {
  id: number;
  code: string;
  name: string;
  active: boolean;
  creationDate?: string;
  modificationDate?: string;
}

export interface ProductStatus {
  id: number;
  code: string;
  name: string;
  active: boolean;
  creationDate?: string;
  modificationDate?: string;
}

export interface FinancialProduct {
  id: number;
  clientId: number;
  productTypeId: number;
  productType: string;
  productStatusId: number;
  productStatus: string;
  accountNumber: string;
  balance: number;
  availableBalance: number;
  exemptGmf: boolean;
  creationDate?: string;
  modificationDate?: string;
}

export interface FinancialProductRequest {
  clientId?: number;
  productTypeId?: number;
  initialBalance?: number;
  exemptGmf?: boolean;
  newStatusId?: number;
}
