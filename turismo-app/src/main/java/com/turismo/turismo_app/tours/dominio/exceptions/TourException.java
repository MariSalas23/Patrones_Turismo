package com.turismo.turismo_app.tours.dominio.exceptions;

public class TourException extends RuntimeException {

    private final String campo;
    private final String valor;

    public TourException(String mensaje, String campo, String valor) {
        super(mensaje);
        this.campo = campo;
        this.valor = valor;
    }

    public String getCampo() { return campo; }
    public String getValor() { return valor; }
}