package com.turismo.turismo_app.usuarios.infraestructura.out.persistance;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;

@Repository
public interface SpringDataUsuarioRepository extends MongoRepository<Usuario, String> {
}