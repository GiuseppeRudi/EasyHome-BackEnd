package it.unical.demacs.informatica.easyhomebackend.persistence.dao;


import it.unical.demacs.informatica.easyhomebackend.model.Utente;

import java.util.List;

public interface UserDao {


    public Utente findByPrimaryKey(String username);

    public void save(Utente utente);


    List<String> findAllUsernames();
}