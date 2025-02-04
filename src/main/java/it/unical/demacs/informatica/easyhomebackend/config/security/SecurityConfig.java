package it.unical.demacs.informatica.easyhomebackend.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disabilita CSRF
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/api/login").permitAll() // Accesso senza autenticazione
                        .requestMatchers("/api/open/**").permitAll() // Accesso senza autenticazione
                        .requestMatchers("/api/admin/**").hasAnyRole("ADMIN" )// Accesso senza autenticazione
                        .requestMatchers("/api/auth/**").authenticated() // Richiede autenticazione
                        .anyRequest().authenticated() // Tutte le altre richieste richiedono autenticazione
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)) // Gestione delle richieste non autorizzate
                )
                .formLogin(form -> form
                        .loginProcessingUrl("/api/login") // Endpoint per il login
                        .successHandler((req, res, auth) -> {
                            // Ottieni il nome e il ruolo dell'utente autenticato
                            String username = auth.getName();
                            String role = auth.getAuthorities().stream()
                                    .map(authority -> authority.getAuthority())
                                    .findFirst()
                                    .orElse("ROLE_USER");

                            String jsonResponse = String.format("{\"username\": \"%s\", \"role\": \"%s\"}", username, role);
                            res.setStatus(200);
                            res.setContentType("application/json");
                            res.getWriter().write(jsonResponse);
                        })
                        .failureHandler((req, res, ex) -> {
                            ex.printStackTrace();
                            res.setStatus(401);
                            res.setContentType("application/json");
                            res.getWriter().write("{\"error\": \"Credenziali errate.\"}");
                        })
                )
                .logout(logout -> logout
                        .logoutUrl("/api/logout") // Endpoint per il logout
                        .logoutSuccessHandler((req, res, auth) -> {
                            // Invalidare la sessione al logout
                            req.getSession().invalidate();  // Invalida la sessione
                            res.setStatus(200);  // Risposta di successo
                        })
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Crea la sessione se necessario
                        .invalidSessionUrl("/api/login") // URL di reindirizzamento se la sessione è invalida
                        .maximumSessions(1) // Limita il numero di sessioni simultanee per utente
                        .expiredUrl("/api/login?expired=true") // URL di reindirizzamento quando la sessione è scaduta
                );

        return http.build();
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utilizza BCrypt per la codifica delle password
    }


}