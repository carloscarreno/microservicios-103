package com.aironmountain.launch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aironmountain.launch.service.AuthService;
import com.aironmountain.launch.service.TokenResponse;


@RestController
@RequestMapping("/launch")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

  /*@GetMapping("/token")
    public ResponseEntity<TokenResponse> getToken() {
        TokenResponse response = authService.login("juan", "redhat1234");
        return ResponseEntity.ok(response);
    } */

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> obtenerToken(@RequestBody AuthRequest authRequest) {
        TokenResponse response = authService.login(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/private-zone")
    public ResponseEntity<String> privateZone(@RequestBody AuthRequest authRequest) {
        TokenResponse response = authService.login(authRequest.getUsername(), authRequest.getPassword());
        String token = response.getAccessToken();
        String privateZoneResponse = authService.privateZone("Bearer "+token);
        return ResponseEntity.ok(privateZoneResponse);       
    }
    
    @PostMapping("/public-zone")
    public ResponseEntity<String> publicZone(@RequestBody AuthRequest authRequest) {
        TokenResponse response = authService.login(authRequest.getUsername(), authRequest.getPassword());
        String token = response.getAccessToken();
        String publicZoneResponse = authService.publicZone("Bearer "+token);
        return ResponseEntity.ok(publicZoneResponse); 
    }
}