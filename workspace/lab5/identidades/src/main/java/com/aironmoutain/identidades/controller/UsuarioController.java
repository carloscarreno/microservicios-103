package com.aironmoutain.identidades.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aironmoutain.identidades.model.Usuario;
import com.aironmoutain.identidades.model.UsuarioDTO;
import com.aironmoutain.identidades.model.UsuarioMapper;
import com.aironmoutain.identidades.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    
    @PostMapping
    public ResponseEntity<String> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario entity=UsuarioMapper.toEntity(usuarioDTO);
        usuarioService.guardar(entity);
        return ResponseEntity.ok("Usuario creado correctamente");
    }

   @GetMapping
    public List<UsuarioDTO> listar() {
        List<Usuario> lista = usuarioService.listar();
        List<UsuarioDTO> listaDTO =  new ArrayList<UsuarioDTO>();
        for(Usuario entity:lista){
            listaDTO.add(UsuarioMapper.toDto(entity));
        }
        return listaDTO;
    }

    @GetMapping("/{id}")
    public UsuarioDTO obtener(@PathVariable Long id) {
        Usuario entity = usuarioService.buscarPorId(id);
        return UsuarioMapper.toDto(entity);
    }

    @GetMapping("/username/{username}")
    public UsuarioDTO buscar(@PathVariable String username) {
        Usuario entity = usuarioService.buscarPorUsername(username);
        return UsuarioMapper.toDto(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario entity=UsuarioMapper.toEntity(usuarioDTO);
        usuarioService.actualizar(id, entity);
        return ResponseEntity.ok("Usuario actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}