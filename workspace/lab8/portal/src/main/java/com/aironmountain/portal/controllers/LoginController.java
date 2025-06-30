package com.aironmountain.portal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Carga login.html desde src/main/resources/templates/
    }

    
}