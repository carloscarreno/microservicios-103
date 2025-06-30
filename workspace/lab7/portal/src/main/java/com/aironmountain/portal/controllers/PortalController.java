package com.aironmountain.portal.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/portal")
public class PortalController {
  
  @Autowired
  private SessionRegistry sessionRegistry;

  @GetMapping("/public")
  public ResponseEntity<String> publico() {
      return ResponseEntity.ok("Esta es una zona publico, precaucion!");
  }
  

   @GetMapping("/private")
  public ResponseEntity<String> privado() {
      return ResponseEntity.ok("Esta es una zona segura, estas a salvo!");
  }
      
   @GetMapping("/sesiones")
   public ResponseEntity<?> getSesiones(){
     String sessionId = "";
     User  entity = null;
     
     List<Object> sesiones = sessionRegistry.getAllPrincipals();
     for (Object sesion:sesiones){
        if(sesion instanceof User){
          entity = (User) sesion;
        }
        List<SessionInformation> detalleSesiones = sessionRegistry.getAllSessions(sesion, false);
        for(SessionInformation detalleSesion : detalleSesiones){
          sessionId = detalleSesion.getSessionId();
        }
      }
      Map<String, Object> response = new HashMap<>();
      response.put("response", "APIs del Portal Corporativo");
      response.put("sessionId", sessionId);
      response.put("sessionData", entity);
      return ResponseEntity.ok(response);
   }

}
