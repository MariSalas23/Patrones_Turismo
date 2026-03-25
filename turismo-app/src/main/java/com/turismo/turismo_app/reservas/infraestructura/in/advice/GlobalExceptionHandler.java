package com.turismo.turismo_app.reservas.infraestructura.in.advice;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.turismo.turismo_app.usuarios.dominio.exceptions.UsuarioException;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<?> handleUsuarioException(UsuarioException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
            "mensaje", ex.getMessage(),
            "campo", ex.getCampo(),
            "valor", ex.getValor(),
            "timestamp", LocalDateTime.now()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
            "mensaje", "Error interno del servidor",
            "detalle", ex.getMessage(),
            "timestamp", LocalDateTime.now()
        ));
    }
}