package com.aironmountain.portal.controllers;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.aironmountain.portal.model.EnumRole;
import com.aironmountain.portal.model.Rol;
import com.aironmountain.portal.model.Usuario;
import com.aironmountain.portal.model.dto.UsuarioDTO;
import com.aironmountain.portal.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/portal/principals")
public class PrincipalController {

    @Autowired
    private UsuarioRepository usuarioRepository;

   @GetMapping("/publico") 
   public String  publico(){
     return "zona publica";
   }
 

   @GetMapping("/privado") 
   public String  privado(){
     return "zona segura";
   }
 
   @PostMapping("/usuario")
   public ResponseEntity<?> createUsuario(@Valid @RequestBody UsuarioDTO dto){
      
    Set<Rol> roles = dto.getRoles().stream()
                       .map(rol -> Rol.builder()
                             .name(EnumRole.valueOf(rol))
                             .build())
                 .collect(Collectors.toSet());            

    Usuario usuario = Usuario.builder()
      .username(dto.getUsername())
      .password(dto.getPassword())
      .email(dto.getEmail())
      .roles(roles)
      .build();

      usuarioRepository.save(usuario);
      return ResponseEntity.ok(usuario);
     }

     @DeleteMapping ("/delete")
     public String deleteUsuario(@RequestParam String id){
          usuarioRepository.deleteById(Long.parseLong(id));
          return "Usuario eliminado con exito, ".concat(id);
   }


}
