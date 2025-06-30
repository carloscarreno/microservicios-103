package com.aironmountain.portal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aironmountain.portal.model.Usuario;
import java.util.Optional;


public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
     
    Optional<Usuario> findByUsername (String username);
    
    @Query("select o from Usuario o where o.username=?1")
    Optional<Usuario> getByUsername(String username);
    
}
