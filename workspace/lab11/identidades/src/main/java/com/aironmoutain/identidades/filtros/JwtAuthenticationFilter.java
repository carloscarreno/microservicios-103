package com.aironmoutain.identidades.filtros;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.aironmoutain.identidades.model.Usuario;
import com.aironmoutain.identidades.service.JwtService;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  

    private JwtService jwtService; 

    public JwtAuthenticationFilter(JwtService jwtService){
        this.jwtService = jwtService;
    }


     @Override
     public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
             throws AuthenticationException {
         Usuario usuario = null;
         String username = "";
         String password = "";
         try{
           usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
           username = usuario.getUsername();
           password = usuario.getPassword();
         }catch( StreamReadException e ){
             throw new RuntimeException(e);
         } catch(DatabindException e){
             throw new RuntimeException(e);
         } catch(IOException e){
            throw new RuntimeException(e);
         }

         UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(username, password);
         return getAuthenticationManager().authenticate(authenticationToken);
     }

     @Override
     protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
             Authentication authResult) throws IOException, ServletException {
         // Si la autenticacion es exito se genera el token
         User user = (User) authResult.getPrincipal();
         String token = jwtService.generateToken(user.getUsername());
         response.addHeader("Authorization", token);
         Map<String, Object> httpResponse =  new HashMap<>();
         httpResponse.put("token", token);
         httpResponse.put("Message", "Autenticacion Exitosa");
         httpResponse.put("username", user.getUsername());
         response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
         response.setStatus(HttpStatus.OK.value());
         response.setContentType(MediaType.APPLICATION_JSON_VALUE);
         response.getWriter().flush();
         super.successfulAuthentication(request, response, chain, authResult);
     }


}
