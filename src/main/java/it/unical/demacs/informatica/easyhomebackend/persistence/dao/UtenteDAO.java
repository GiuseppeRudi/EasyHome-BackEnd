package it.unical.demacs.informatica.easyhomebackend.persistence.dao;

import it.unical.demacs.informatica.easyhomebackend.persistence.model.Utente;

import java.util.List;

public interface UtenteDAO {
    List<Utente> findAll();
    void save(Utente utente);
}
