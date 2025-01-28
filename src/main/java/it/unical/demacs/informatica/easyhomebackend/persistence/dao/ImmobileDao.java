package it.unical.demacs.informatica.easyhomebackend.persistence.dao;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;

import java.util.List;
import java.util.Optional;

/**
 * Interfaccia DAO per la gestione degli immobili.
 */
public interface ImmobileDao {

        /**
         * Salva un immobile nel database.
         *
         * @param immobile l'oggetto Immobile da salvare
         * @throws IllegalArgumentException se l'immobile è null o contiene dati non validi
         */
        void save(Immobile immobile);

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
