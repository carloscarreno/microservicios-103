package com.aironmoutain.identidades.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.aironmoutain.identidades.model.Usuario;
import com.aironmoutain.identidades.repository.UsuarioRepository;

@Service
public class UsuarioService {

   private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario guardar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Usuario buscarPorUsername(String username){
        return repository.findByUsername(username);
    }
    
    public Usuario actualizar(Long id, Usuario nuevo) {
        Optional<Usuario> encontrado = repository.findById(id);
        if (encontrado.isPresent()) {
            Usuario usuario = encontrado.get();
            usuario.setNombre(nuevo.getNombre());
            usuario.setCorreo(nuevo.getCorreo());
            usuario.setId(nuevo.getId());
            return repository.save(usuario);
        }
        return null;
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
