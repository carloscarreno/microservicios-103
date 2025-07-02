package com.aironmountain.bitcoins.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.aironmountain.bitcoins.service.dto.UsuarioDTO;

@FeignClient(name = "identidades")
public interface IdentidadCliente {

    @GetMapping("/api/usuarios/username/{username}")
    UsuarioDTO obtenerUsuario(@PathVariable String username); 

   
}
