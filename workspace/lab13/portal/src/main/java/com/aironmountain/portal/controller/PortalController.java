package com.aironmountain.portal.controller;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
//@RequestMapping("/api/portal")
public class PortalController {

       @GetMapping("/welcome")
       // Cualquier usuario
        public String welcome() {
             return "Bienvenidos todos! ";
        }

        // Cualquier usuario, para aplicar restricciones a los usuarios mediante roles
        // es necesario @EnableMethodSecurity en la clase de configuracion
        @GetMapping("/public-zone")
        @PreAuthorize("hasRole('user_client') or hasRole('admin_client')")
        public String zonaPublica() {
             return "Precaucion estas en una zona publica - USER";
        }
        
       @GetMapping("/private-zone")
       @PreAuthorize("hasRole('admin_client')") 
        public String zonaPrivada() {
             return "Estas a salvo, estas en una zona segura - ADMIN";
        }
        
}
