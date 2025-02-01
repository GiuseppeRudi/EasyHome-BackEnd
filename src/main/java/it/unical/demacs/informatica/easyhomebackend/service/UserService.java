package it.unical.demacs.informatica.easyhomebackend.service;

import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.persistence.DBManager;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService implements IUserService, UserDetailsService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.userDao = DBManager.getInstance().getUserDao();
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(Utente utente) {
        // TODO checks: fields are not null, password complexity, username not already used
        this.userDao.save(new Utente(utente.getUsername(), passwordEncoder.encode(utente.getPassword()), utente.getRole(),utente.getNome(),utente.getCognome(),utente.getData_nascita(),utente.getNazionalita(),utente.getEmail()));
        this.getUser(utente.getUsername()).get();
    }

    @Override
    public Optional<Utente> getUser(String username) {
        return Optional.ofNullable(this.userDao.findByPrimaryKey(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utente> userOpt = this.getUser(username);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        Utente user = userOpt.get();
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().name())) // Correzione qui: mappa il ruolo direttamente
        );
    }

}
