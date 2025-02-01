package it.unical.demacs.informatica.easyhomebackend.service;

import it.unical.demacs.informatica.easyhomebackend.model.UserRole;
import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.persistence.DBManager;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.UserDao;
import it.unical.demacs.informatica.easyhomebackend.persistence.dto.UserRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
    public Utente createUser(Utente utente) {
        // TODO checks: fields are not null, password complexity, username not already used
        this.userDao.save(new Utente(utente.getUsername(), passwordEncoder.encode(utente.getPassword()), utente.getRole(),utente.getNome(),utente.getCognome(),utente.getData_nascita(),utente.getNazionalita(),utente.getEmail()));
        return this.getUser(utente.getUsername()).get();
    }

    @Override
    public Optional<Utente> getUser(String username) {
        Utente byPrimaryKey = this.userDao.findByPrimaryKey(username);
        return byPrimaryKey == null ? Optional.empty() : Optional.of(byPrimaryKey);
    }



    @Override
    public List<UserRoleDto> getAllUsernamesAndRoles() {
        return this.userDao.findAllUsernamesAndRoles();
    }

    @Override
    public void changeUserRole(String username, UserRole newRole) {
        this.userDao.changeUserRole(username, newRole);
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
    public boolean deleteUser(String username) {
        Utente user = userDao.findByPrimaryKey(username); // Cerca l'utente per username
        if (user != null) {
            try {
                userDao.delete(user); // Elimina l'utente dal database
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false; // In caso di errore durante l'eliminazione
            }
        }
        return false; // Utente non trovato
    }



}
