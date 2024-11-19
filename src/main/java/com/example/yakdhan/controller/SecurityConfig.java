package com.example.yakdhan.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS
                .csrf(csrf -> csrf.disable()) // Disable CSRF for REST APIs
                .authorizeRequests(authz -> authz
                        // Public paths
                        .requestMatchers("/api/auth/signup", "/api/auth/login", "/api/auth/forgot-password").permitAll() // Allow access to authentication-related endpoints
                        .requestMatchers(HttpMethod.POST, "/api/auth/signup/step2").permitAll() // Specific POST request
                        .requestMatchers("/api/classes", "/error").permitAll() // Allow access to public class and error endpoints
                        
                        // Public command paths
                        .requestMatchers("/api/commandes/**").permitAll()  // Allow access to /api/commandes without authentication
                        .requestMatchers("/api/installations/**").permitAll()

                        // Authentication protected paths
                        .requestMatchers("/api/auth/**").authenticated() // All authentication-related paths require authentication
                        
                        // Any other path requires authentication
                        .anyRequest().authenticated()
                )
                .httpBasic(httpSecurity -> { }); // Basic HTTP Authentication

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Set your frontend URL
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow these HTTP methods
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Allow these headers
        configuration.setAllowCredentials(true); // Allow credentials

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply the configuration to all endpoints
        return source;
    }
}
