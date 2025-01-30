package it.unical.demacs.informatica.easyhomebackend.service;

import it.unical.demacs.informatica.easyhomebackend.model.Recensione;
import it.unical.demacs.informatica.easyhomebackend.persistence.DBManager;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.RecensioneDao;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // Indica che è un service gestito da Spring
public class RecensioneService {

    private final RecensioneDao recensioneDao = DBManager.getInstance().getRecensioneDao();

    public RecensioneService() {
    }

    public Recensione creaRecensione(int rating, String descrizione) {
        // Validazione dei campi obbligatori
        if (rating < 1 || rating > 5 || descrizione == null || descrizione.isEmpty()) {
            throw new IllegalArgumentException("I dati della recensione non sono validi");
        }

        // Creazione della recensione senza ID (sarà generato automaticamente)
        Recensione nuovaRecensione = new Recensione();
        nuovaRecensione.setRating(rating);
        nuovaRecensione.setDescrizione(descrizione);

        try {
            // Salvataggio della recensione nel database
            recensioneDao.save(nuovaRecensione);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il salvataggio della recensione", e);
        }

        // Restituisco la recensione con l'ID generato
        return nuovaRecensione;
    }

}
