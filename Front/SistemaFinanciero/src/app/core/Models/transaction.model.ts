export interface MovementType {
  id: number;
  code: string;
  name: string;
  active: boolean;
  creationDate?: string;
  modificationDate?: string;
}

export interface Movement {
  id: number;
  productId: number;
  movementType: string;
  amount: number;
  previousBalance: number;
  newBalance: number;
  description?: string;
  movementDate: string;
}

export interface MovementRequest {
  productId: number;
  amount: number;
  description?: string;
}

export interface Transfer {
  id: number;
  transferDate: string;
  originMovement: Movement;
  destinationMovement: Movement;
}

export interface TransferRequest {
  originProductId: number;
  destinationProductId: number;
  amount: number;
  description?: string;
}
