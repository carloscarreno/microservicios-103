package com.aironmoutain.identidades.service;
import org.springframework.stereotype.Service;

import com.aironmoutain.identidades.model.Rol;
import com.aironmoutain.identidades.repository.RolRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> listar() {
        return (List<Rol>) rolRepository.findAll();
    }

    public Rol buscarPorId(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    public Rol crear(Rol rol) {
        return rolRepository.save(rol);
    }

    public void eliminar(Long id) {
        rolRepository.deleteById(id);
    }

    public Rol buscarPorNombre(String nombre) {
        return rolRepository.findByNombre(nombre);
    }

    public Optional<Rol> buscarOptional(Long id) {
        return rolRepository.findById(id);
    }
}

