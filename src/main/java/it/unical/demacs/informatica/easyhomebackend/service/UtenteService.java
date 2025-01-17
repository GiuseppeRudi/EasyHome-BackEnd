package it.unical.demacs.informatica.easyhomebackend.service;

import it.unical.demacs.informatica.easyhomebackend.persistence.DBManager;
import it.unical.demacs.informatica.easyhomebackend.persistence.model.Utente;

import java.util.List;

public class UtenteService {


    public List<Utente> findAll() {
        return DBManager.getInstance().getUtenteDao().findAll();
    }

    public void save(Utente utente) {
        DBManager.getInstance().getUtenteDao().save(utente);
    }
}
