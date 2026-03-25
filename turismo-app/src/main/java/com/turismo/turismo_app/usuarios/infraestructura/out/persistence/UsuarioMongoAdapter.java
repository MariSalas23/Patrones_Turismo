package com.turismo.turismo_app.usuarios.infraestructura.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;
import com.turismo.turismo_app.usuarios.dominio.ports.UsuarioRepositoryPort;

@Component
public class UsuarioMongoAdapter implements UsuarioRepositoryPort {

    private final SpringDataUsuarioRepository repository;

    public UsuarioMongoAdapter(SpringDataUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void eliminarPorId(String id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return repository.findAll()
                .stream()
                .anyMatch(u -> u.getCorreo().equalsIgnoreCase(correo));
    }
}