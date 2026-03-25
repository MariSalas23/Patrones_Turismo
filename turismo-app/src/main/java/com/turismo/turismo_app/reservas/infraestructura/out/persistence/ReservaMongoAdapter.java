package com.turismo.turismo_app.reservas.infraestructura.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.turismo.turismo_app.reservas.dominio.entities.Reserva;
import com.turismo.turismo_app.reservas.dominio.ports.ReservaRepositoryPort;

@Component
public class ReservaMongoAdapter implements ReservaRepositoryPort {

    private final SpringDataReservaRepository repository;

    public ReservaMongoAdapter(SpringDataReservaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reserva guardar(Reserva reserva) {
        ReservaDocument doc = ReservaDocument.fromDomain(reserva);
        return repository.save(doc).toDomain();
    }

    @Override
    public Optional<Reserva> buscarPorId(String id) {
        return repository.findById(id)
                .map(ReservaDocument::toDomain);
    }

    @Override
    public List<Reserva> buscarPorUsuario(String usuarioId) {
        return repository.findByUsuarioId(usuarioId)
                .stream()
                .map(ReservaDocument::toDomain)
                .toList();
    }

    @Override
    public List<Reserva> buscarTodas() {
        return repository.findAll()
                .stream()
                .map(ReservaDocument::toDomain)
                .toList();
    }
}