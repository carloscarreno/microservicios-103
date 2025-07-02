package com.aironmoutain.identidades.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aironmoutain.identidades.model.Usuario;
import com.aironmoutain.identidades.model.Rol;
import com.aironmoutain.identidades.repository.RolRepository;
import com.aironmoutain.identidades.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
   private final UsuarioRepository repository;
   
   @Autowired
   private RolRepository rolRepository;

   public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Iterable<Usuario> listar() {
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

    public void asignarRol(Long idUsuario,Long idRol){
       Optional<Rol> rol= rolRepository.findById(idRol);
       Optional<Usuario> usuario = repository.findById(idUsuario);
       Usuario entity = null;
       if(usuario.isPresent() && rol.isPresent()){
             entity = usuario.get();
             entity.getRoles().add(rol.get());
       }
       actualizar(idUsuario, entity);
    }

}
