package com.aironmoutain.identidades.repository;


import org.springframework.data.repository.CrudRepository;

import com.aironmoutain.identidades.model.Rol;

public interface RolRepository extends CrudRepository<Rol, Long> {
     Rol findByNombre(String nombre);
}
