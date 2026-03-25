package com.turismo.turismo_app.usuarios.dominio.exceptions;

public class UsuarioException extends RuntimeException {

    private final String campo;
    private final String valor;

    public UsuarioException(String mensaje, String campo, String valor) {
        super(mensaje);
        this.campo = campo;
        this.valor = valor;
    }

    public String getCampo() { return campo; }
    public String getValor() { return valor; }
}