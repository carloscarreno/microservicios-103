package com.aironmoutain.identidades.model;

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
        //TODO: Indicar que el dato password es confidencial
        return dto;
    }

}
