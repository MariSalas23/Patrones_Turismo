package com.turismo.turismo_app.reservas.infraestructura.out.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataReservaRepository extends MongoRepository<ReservaDocument, String> {

    List<ReservaDocument> findByUsuarioId(String usuarioId);
}