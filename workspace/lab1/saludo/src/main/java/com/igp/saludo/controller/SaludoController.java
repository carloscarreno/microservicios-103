package com.igp.saludo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class SaludoController {
    @GetMapping("/") 
   public String hola(){
     return "Hola Mundo desde Spring Boot!";
   }
}
