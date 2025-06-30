package com.aironmountain.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
     public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
         return httpSecurity
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/portal/public").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin(form -> form
                 // .loginPage("/login")
                 // .successHandler(successHandler())
                    .defaultSuccessUrl("/api/portal/private", true)
                    .permitAll())
                .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                    .sessionConcurrency(concurrency -> concurrency
                        .maximumSessions(1)
                        .expiredUrl("/login?expired")
                        .maxSessionsPreventsLogin(false)
                        .sessionRegistry(sessionRegistry())
                )
                //    .invalidSessionUrl("/login")
                //    .maximumSessions(1)
                //    .expiredUrl("/login")
                //    .sessionRegistry(sessionRegistry())
                 )
                .httpBasic(withDefaults())
                .csrf(csrf -> csrf.disable())
                 .build();
     }         


    @Bean
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
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    public AuthenticationSuccessHandler successHandler(){
        return ((request, response, authentication) -> {
            response.sendRedirect("/api/portal/private"); 
        });        
    }

// Nota:
// Si estás usando login por formulario (formLogin), 
// Spring Security ya migra el ID de sesión automáticamente tras login exitoso.

   @Bean
   public SessionRegistry  sessionRegistry(){
    return new SessionRegistryImpl();
   }


   @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public static HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

}
