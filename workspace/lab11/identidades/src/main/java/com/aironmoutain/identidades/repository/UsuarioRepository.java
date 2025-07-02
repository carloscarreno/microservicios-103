package com.aironmoutain.identidades.repository;
import org.springframework.data.repository.CrudRepository;

import com.aironmoutain.identidades.model.Usuario;



public interface  UsuarioRepository extends CrudRepository<Usuario, Long>  {
     Usuario findByUsername(String username);
     
}

