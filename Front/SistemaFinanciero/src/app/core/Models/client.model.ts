export interface DocumentType {
  id: number;
  code: string;
  name: string;
  active: boolean;
  creationDate?: string;
  modificationDate?: string;
}

export interface Client {
  id: number;
  tipoDocumentoId: number;
  numeroDocumento: string;
  nombres: string;
  apellidos: string;
  correo: string;
  fechaNacimiento: string;
  fechaCreacion?: string;
  fechaModificacion?: string;
}

export interface ClientRequest {
  tipoDocumentoId: number;
  numeroDocumento: string;
  nombres: string;
  apellidos: string;
  correo: string;
  fechaNacimiento: string;
}
