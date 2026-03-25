package com.turismo.turismo_app.usuarios.infraestructura.out.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;

public interface SpringDataUsuarioRepository extends MongoRepository<Usuario, String> {
    boolean existsByCorreo(String correo);
}