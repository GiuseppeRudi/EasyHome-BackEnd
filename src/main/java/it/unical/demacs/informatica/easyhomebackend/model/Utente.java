package it.unical.demacs.informatica.easyhomebackend.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.time.LocalDate;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class Utente implements UserDetails {


    private String username;
    private String password;
    private UserRole role;
    private String nome;
    private String cognome;
    private String data_nascita;
//    private String provincia_di_nascita;
//    private String comune_di_nascita;
    private String nazionalita;
    private String email;


    public Utente(String username, String password, UserRole role, String nome,String cognome,String data_nascita,String nazionalita, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.nazionalita= nazionalita;
        this.nome = nome;
        this.cognome = cognome;
        this.data_nascita = data_nascita;
        this.email= email;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return role.toString();
                    }
                }
        );
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}