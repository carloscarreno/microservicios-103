package com.aironmountain.launch.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "keycloak", url = "http://192.168.56.104:8080")
public interface KeycloakClient {

    @PostMapping(value = "/realms/{realm}/protocol/openid-connect/token",
                 consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    TokenResponse getToken(@PathVariable("realm") String realm,
                           @RequestBody MultiValueMap<String, String> form);


    

}