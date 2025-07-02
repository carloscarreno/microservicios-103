package com.aironmoutain.identidades.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.aironmoutain.identidades.filtros.JwtAuthenticationFilter;
import com.aironmoutain.identidades.service.JwtService;
import com.aironmoutain.identidades.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;


@Configuration
public class SecurityConfig {

   @Autowired
   JwtService jwtService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception{
       JwtAuthenticationFilter jwtAuthenticationFilter =  new JwtAuthenticationFilter(jwtService); 
       jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
       jwtAuthenticationFilter.setFilterProcessesUrl("/login");
       return httpSecurity
                .csrf(csrf -> csrf.disable()) 
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/identidades/zona-publica",
                                                      "/api/identidades/usuarios",
                                                      "/api/identidades/roles",
                                                      "/api/identidades/asignar/usuario/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
               // .httpBasic(withDefaults()) // Nosostros vamos a quere autenticarnos con un token 
               .addFilter(jwtAuthenticationFilter) 
               .build();
    }

    /*  @Bean
    public UserDetailsService users(PasswordEncoder encoder) {
       UserDetails admin = User.builder()
            .username("admin")
            .password(encoder.encode("changeit!"))
            .roles("ADMIN")
            .build();

        UserDetails user = User.builder()
            .username("usuario")
            .password(encoder.encode("1234"))
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(admin, user);
    } */
    
    

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //Requisito de Spring Security 6
    }
    
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
        //return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
        //           .userDetailsService(userDetailsService)
        //           .passwordEncoder(passwordEncoder)
        //           .and().build();
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);   
    }
}
