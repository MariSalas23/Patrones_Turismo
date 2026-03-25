package com.turismo.turismo_app.reservas.infraestructura.out.external;

import org.springframework.stereotype.Component;

import com.turismo.turismo_app.reservas.dominio.ports.PagoPort;

@Component
public class PagoGatewayAdapter implements PagoPort {

    @Override
    public boolean procesarPago(String reservaId) {
        return true; // Simulación
    }
}