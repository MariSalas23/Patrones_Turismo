package com.turismo.turismo_app.usuarios.dominio.exceptions;

public class CorreoInvalidoException extends UsuarioException {
    public CorreoInvalidoException(String correo) {
        super("Formato de correo inválido", "correo", correo);
    }
}