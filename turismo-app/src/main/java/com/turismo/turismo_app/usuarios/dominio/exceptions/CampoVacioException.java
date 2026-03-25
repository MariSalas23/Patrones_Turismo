package com.turismo.turismo_app.usuarios.dominio.exceptions;

public class CampoVacioException extends UsuarioException {
    public CampoVacioException(String campo) {
        super("El campo es obligatorio", campo, null);
    }
}