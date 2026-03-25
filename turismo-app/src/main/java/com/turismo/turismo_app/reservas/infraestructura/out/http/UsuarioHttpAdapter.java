package com.turismo.turismo_app.reservas.infraestructura.out.http;

import org.springframework.stereotype.Component;

import com.turismo.turismo_app.reservas.dominio.ports.UsuarioClientPort;

@Component
public class UsuarioHttpAdapter implements UsuarioClientPort {

    @Override
    public boolean existeUsuario(String usuarioId) {
        // Simulación
        return true;
    }
}