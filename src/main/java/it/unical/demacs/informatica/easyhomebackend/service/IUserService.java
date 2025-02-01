package it.unical.demacs.informatica.easyhomebackend.service;


import it.unical.demacs.informatica.easyhomebackend.model.Utente;

import java.util.Optional;

public interface IUserService {

    void createUser(Utente utente);


    Optional<Utente> getUser(String username);



}