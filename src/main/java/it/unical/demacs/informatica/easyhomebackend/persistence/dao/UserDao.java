package it.unical.demacs.informatica.easyhomebackend.persistence.dao;


import it.unical.demacs.informatica.easyhomebackend.model.Utente;

public interface UserDao {


    Utente findByPrimaryKey(String username);

    void save(Utente utente);
    void update(Utente utente);
    void deleteByUsername(String username);


}