package it.unical.demacs.informatica.easyhomebackend.persistence.dao;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.model.ImmobileMinimal;

import java.sql.SQLException;
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
        void save(Immobile immobile, String user);

        List<Immobile> findFiltered(String tipo, String categoria, String provincia);
        List<byte[]> getImmagini(Integer id) throws Exception;

        /**
         * Cerca un immobile nel database utilizzando il suo ID.
         *
         * @param id l'ID dell'immobile da cercare
         * @return un Optional contenente l'immobile se trovato, altrimenti vuoto
         */
        Immobile findByPrimaryKey(int id);

        List<Immobile> findByUserId(String username);

        /**
         * Restituisce tutti gli immobili presenti nel database.
         *
         * @return una lista di immobili
         */
        List<Immobile> findAll();

    List<ImmobileMinimal> getImmobiliFilteredMinimal(String tipo, String categoria, String provincia);

        void deleteimmobileID(int id) throws SQLException;
}
