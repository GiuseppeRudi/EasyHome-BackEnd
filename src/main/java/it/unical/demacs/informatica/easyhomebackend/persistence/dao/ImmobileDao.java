package it.unical.demacs.informatica.easyhomebackend.persistence.dao;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Interfaccia DAO per la gestione degli immobili.
 */
public interface ImmobileDao {

        /**
         * Salva un immobile nel database.
         *
         * @throws IllegalArgumentException se l'immobile è null o contiene dati non validi
         */
        void save(String nome, String tipo, String descrizione, String categoria, int prezzo, int mq, int camere, int bagni, int anno, String etichetta, String indirizzo,List<MultipartFile> foto);

        List<Immobile> findFiltered(String tipo, String affittoVendita, String luogo);

        /**
         * Cerca un immobile nel database utilizzando il suo ID.
         *
         * @param id l'ID dell'immobile da cercare
         * @return un Optional contenente l'immobile se trovato, altrimenti vuoto
         */
        Immobile findByPrimaryKey(String id);

        /**
         * Restituisce tutti gli immobili presenti nel database.
         *
         * @return una lista di immobili
         */
        List<Immobile> findAll();
}
