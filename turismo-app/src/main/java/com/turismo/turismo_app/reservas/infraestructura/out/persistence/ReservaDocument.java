package com.turismo.turismo_app.reservas.infraestructura.out.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.turismo.turismo_app.reservas.dominio.entities.Reserva;
import com.turismo.turismo_app.reservas.dominio.entities.EstadoReserva;

import java.time.LocalDateTime;

@Document(collection = "reservas")
public class ReservaDocument {

    @Id
    private String id;
    private String usuarioId;
    private String tourId;
    private int cantidadPersonas;
    private LocalDateTime fechaReserva;
    private EstadoReserva estado;

    public ReservaDocument() {}

    public static ReservaDocument fromDomain(Reserva reserva) {
        ReservaDocument doc = new ReservaDocument();
        doc.id = reserva.getId();
        doc.usuarioId = reserva.getUsuarioId();
        doc.tourId = reserva.getTourId();
        doc.cantidadPersonas = reserva.getCantidadPersonas();
        doc.fechaReserva = reserva.getFechaReserva();
        doc.estado = reserva.getEstado();
        return doc;
    }

    public Reserva toDomain() {
        Reserva reserva = new Reserva(
                this.id,
                this.usuarioId,
                this.tourId,
                this.cantidadPersonas,
                this.fechaReserva
        );

        if (this.estado == EstadoReserva.CONFIRMADA) {
            reserva.confirmar();
        } else if (this.estado == EstadoReserva.CANCELADA) {
            reserva.cancelar();
        }

        return reserva;
    }

}