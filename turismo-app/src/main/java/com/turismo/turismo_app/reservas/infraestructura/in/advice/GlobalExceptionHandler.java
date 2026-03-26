package com.turismo.turismo_app.reservas.infraestructura.in.advice;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.turismo.turismo_app.reservas.dominio.exceptions.ReservaException;
import com.turismo.turismo_app.tours.dominio.exceptions.TourException;
import com.turismo.turismo_app.usuarios.dominio.exceptions.UsuarioException;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Usuarios
    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<?> handleUsuarioException(UsuarioException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
            "error", "Usuario inválido",
            "mensaje", ex.getMessage(),
            "campo", ex.getCampo(),
            "valor", ex.getValor(),
            "timestamp", LocalDateTime.now()
        ));
    }

    // Reservas
    @ExceptionHandler(ReservaException.class)
    public ResponseEntity<?> handleReservaException(ReservaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
            "error", "Error en reserva",
            "mensaje", ex.getMessage(),
            "timestamp", LocalDateTime.now()
        ));
    }

    // Tours
    @ExceptionHandler(TourException.class)
    public ResponseEntity<?> handleTourException(TourException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
            "error", "Error en tour",
            "mensaje", ex.getMessage(),
            "campo", ex.getCampo(),
            "valor", ex.getValor(),
            "timestamp", LocalDateTime.now()
        ));
    }

    // Error formato
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleJsonError(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
            "error", "Formato inválido",
            "mensaje", "El cuerpo de la solicitud tiene un formato incorrecto (JSON inválido o fecha mal formada)",
            "timestamp", LocalDateTime.now()
        ));
    }

    // Error de tipos
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
            "error", "Tipo de dato inválido",
            "mensaje", "El parámetro '" + ex.getName() + "' tiene un valor inválido",
            "valor", ex.getValue(),
            "timestamp", LocalDateTime.now()
        ));
    }

    // Error general
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
            "error", "Error interno",
            "mensaje", "Error interno del servidor",
            "detalle", ex.getMessage(),
            "timestamp", LocalDateTime.now()
        ));
    }
}