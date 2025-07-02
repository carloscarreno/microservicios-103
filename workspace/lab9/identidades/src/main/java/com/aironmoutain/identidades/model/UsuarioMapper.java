package com.aironmoutain.identidades.model;

import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class UsuarioMapper {

    private UsuarioMapper() {}           // Evita instanciación

    public static Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Usuario entity = new Usuario();
        entity.setNombre(dto.getNombre());
        entity.setCorreo(dto.getCorreo());
        entity.setEdad(dto.getEdad());
        entity.setPassword(dto.getPassword());
        entity.setUsername(dto.getUsername());
        return entity;
    }

    public static UsuarioDTO toDto(Usuario entity) {
        if (entity == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setCorreo(entity.getCorreo());
        dto.setEdad(entity.getEdad());
        dto.setUsername(entity.getUsername());
        dto.setPassword("*****");
        //TODO: Indicar que el dato password es confidencial
        String roles = entity.getRoles().stream()
                             .map(Rol::getNombre)
                             .collect(Collectors.joining(","));
        dto.setRoles(roles);                    
        return dto;
    }

}
