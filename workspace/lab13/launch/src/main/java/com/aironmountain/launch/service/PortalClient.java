package com.aironmountain.launch.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "portal", url = "http://192.168.56.1:8085")
public interface PortalClient {

     @GetMapping("/private-zone")
     public String zonaPrivada(@RequestHeader("Authorization") String token);

     @GetMapping("/public-zone")
     public String zonaPublica(@RequestHeader("Authorization") String token);

}
