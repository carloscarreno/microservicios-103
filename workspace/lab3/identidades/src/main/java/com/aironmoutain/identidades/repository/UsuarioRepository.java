package com.aironmoutain.identidades.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aironmoutain.identidades.model.Usuario;

public interface  UsuarioRepository extends JpaRepository<Usuario, Long>  {

}

