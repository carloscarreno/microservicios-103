package com.aironmoutain.identidades.filtros;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aironmoutain.identidades.service.JwtService;
import com.aironmoutain.identidades.service.UserDetailsServiceImpl;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthorizationFilter  extends OncePerRequestFilter{
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserDetailsServiceImpl UserDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, 
                                    @NonNull  HttpServletResponse response, 
                                    @NonNull  FilterChain filterChain)       throws ServletException, IOException {
        
        String tokenHeader = request.getHeader("Authorization");
        if(tokenHeader != null && tokenHeader.startsWith("Bearer ")){
            String token = tokenHeader.substring(7);

            if(jwtService.isTokenValid(token)){
                String username=jwtService.extractUsername(token);
                UserDetails userDetails = UserDetailsService.loadUserByUsername(username);                
                UsernamePasswordAuthenticationToken authenticationToken = 
                                    new UsernamePasswordAuthenticationToken(username,null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);              
            }           
        }
        filterChain.doFilter(request, response);
    }
    
}
