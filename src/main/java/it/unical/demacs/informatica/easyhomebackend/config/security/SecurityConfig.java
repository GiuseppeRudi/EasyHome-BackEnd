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
                        .requestMatchers("/api/admin/**").hasAnyRole("ADMIN" ) // Accesso senza autenticazione
                        .requestMatchers("/api/auth/**").authenticated() // Richiede autenticazione

                        .anyRequest().authenticated() // Tutte le altre richieste richiedono autenticazione
                )
                .exceptionHandling(ex -> ex
                        // Restituisce 401 Unauthorized per richieste non autorizzate
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .formLogin(form -> form
                        .loginProcessingUrl("/api/login") // Endpoint per il login
                        .successHandler((req, res, auth) -> {
                            // Ottieni il nome e il ruolo dell'utente autenticato
                            String username = auth.getName(); // Username dell'utente
                            String role = auth.getAuthorities().stream()
                                    .map(authority -> authority.getAuthority())
                                    .findFirst() // Se l'utente ha più ruoli, prendi il primo
                                    .orElse("ROLE_USER"); // Default se non ci sono ruoli

                            // Crea la risposta JSON
                            String jsonResponse = String.format("{\"username\": \"%s\", \"role\": \"%s\"}", username, role);

                            // Configura la risposta
                            res.setStatus(200); // HTTP 200
                            res.setContentType("application/json");
                            res.getWriter().write(jsonResponse); // Scrivi il JSON nella risposta
                        })
                        .failureHandler((req, res, ex) -> {
                            ex.printStackTrace(); // Log l'errore
                            res.setStatus(401); // Imposta lo stato HTTP come 401
                            res.setContentType("application/json");
                            res.getWriter().write("{\"error\": \"Credenziali errate.\"}"); // Messaggio JSON di errore
                        })
                )

                .logout(logout -> logout
                        .logoutUrl("/api/logout") // Endpoint per il logout
                        .logoutSuccessHandler((req, res, auth) -> res.setStatus(200)) // Gestione successo logout
                );


        return http.build();
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utilizza BCrypt per la codifica delle password
    }


}