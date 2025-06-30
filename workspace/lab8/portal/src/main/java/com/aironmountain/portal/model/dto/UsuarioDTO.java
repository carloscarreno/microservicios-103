package com.aironmountain.portal.model.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
     //private Long id;
     @NotBlank
     private String username;
     @NotBlank
     private String password;
     @NotBlank
     @Email
     private String email;
     
     private Set<String> roles;
     
}
