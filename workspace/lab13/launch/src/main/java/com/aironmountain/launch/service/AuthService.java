package com.aironmountain.launch.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class AuthService {

    private final KeycloakClient keycloakClient;

    private final PortalClient portalClient;

    public AuthService(KeycloakClient keycloakClient,PortalClient portalClient) {
        this.keycloakClient = keycloakClient;
        this.portalClient = portalClient;
    }

    public TokenResponse login(String username, String password) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "password");
        form.add("client_id", "portal-api");
        form.add("client_secret", "v0xI8aZiEZU63QsB1IrHlMKLm0tT1PRh"); 
        form.add("username", username);
        form.add("password", password);
        return keycloakClient.getToken("lab-realm2", form);
    }

    public String privateZone(String jwtAuth){
       return portalClient.zonaPrivada(jwtAuth);
    }

    public String publicZone(String jwtAuth){
       return portalClient.zonaPublica(jwtAuth);
    }

}
