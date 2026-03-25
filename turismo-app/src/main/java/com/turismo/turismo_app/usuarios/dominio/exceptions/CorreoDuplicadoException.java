package com.turismo.turismo_app.usuarios.dominio.exceptions;

public class CorreoDuplicadoException extends UsuarioException {
    public CorreoDuplicadoException(String correo) {
        super("El correo ya está registrado", "correo", correo);
    }
}