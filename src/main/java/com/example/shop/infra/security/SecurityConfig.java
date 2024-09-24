package com.example.shop.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Desabilitar CSRF para evitar problemas com POST
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll()  // Permitir todas as requisições sem autenticação
                );
        return http.build();
    }
}