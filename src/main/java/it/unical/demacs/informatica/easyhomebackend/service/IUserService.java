package it.unical.demacs.informatica.easyhomebackend.service;


import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.model.UserRole;
import java.util.Optional;

public interface IUserService {

    Utente createUser(Utente utente);


    Optional<Utente> getUser(String username);



}