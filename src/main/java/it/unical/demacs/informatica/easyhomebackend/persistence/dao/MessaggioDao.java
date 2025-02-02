package it.unical.demacs.informatica.easyhomebackend.persistence.dao;

import it.unical.demacs.informatica.easyhomebackend.model.Messaggio;

public interface MessaggioDao {
    void save(Messaggio messaggio, String acquirente);
}
