package it.unical.demacs.informatica.easyhomebackend.service;


import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.model.UserRole;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Utente createUser(Utente utente);

    public boolean deleteUser(String username);
    Optional<Utente> getUser(String username);


    List<String> getAllUsernames();
}