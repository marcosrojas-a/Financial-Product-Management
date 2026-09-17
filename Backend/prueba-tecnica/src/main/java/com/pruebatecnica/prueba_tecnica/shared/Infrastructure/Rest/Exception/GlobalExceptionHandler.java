package com.pruebatecnica.prueba_tecnica.shared.Infrastructure.Rest.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    // Datos inválidos, referencias inexistentes, formato incorrecto, etc.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgument(IllegalArgumentException ex,HttpServletRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex, request);
    }

    // Operación válida en sí misma, pero que rompe una regla de negocio en el estado actual
    // (ej. eliminar cliente con productos asociados, cancelar cuenta con saldo distinto de $0)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalState(IllegalStateException ex,
                                                               HttpServletRequest request) {
        return buildResponse(HttpStatus.CONFLICT, ex, request);
    }

    // Red de seguridad: cualquier otra excepción no controlada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleUnexpected(Exception ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }

    private ResponseEntity<ApiErrorResponse> buildResponse(HttpStatus status, Exception ex,
                                                           HttpServletRequest request) {
        ApiErrorResponse body = new ApiErrorResponse(
                status.value(), status.getReasonPhrase(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }
}
