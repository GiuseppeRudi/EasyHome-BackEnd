package it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc.proxy;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.model.UserRole;
import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.ImmobileDao;

import java.util.List;

public class UtenteProxy extends Utente {
    private final ImmobileDao immobileDAO;
    private boolean immobiliLoaded = false;

    public UtenteProxy(String username, String password, UserRole role,
                       String nome, String cognome, String dataNascita,
                       String nazionalita, String email, ImmobileDao immobileDAO) {
        super(username, password, role, nome, cognome, dataNascita, nazionalita, email);
        this.immobileDAO = immobileDAO;
    }

    @Override
    public List<Immobile> getImmobili() {
        if (!immobiliLoaded) {
            List<Immobile> immobiliCaricati = immobileDAO.findByUserId(this.getUsername());
            super.setImmobili(immobiliCaricati);
            immobiliLoaded = true;
        }
        return immobili;
    }
}
