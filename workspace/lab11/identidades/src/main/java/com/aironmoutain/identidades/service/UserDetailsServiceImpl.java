package com.aironmoutain.identidades.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.aironmoutain.identidades.model.Usuario;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.buscarPorUsername(username);
        
        Collection<? extends GrantedAuthority> authorities = usuario.getRoles().stream()
                          .map( role -> new SimpleGrantedAuthority("ROLE_".concat(role.getNombre())))
                          .collect(Collectors.toSet()); 
                          
        return new User(usuario.getUsername(),
                        usuario.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        authorities );
    }

    
}
