package it.unical.demacs.informatica.easyhomebackend.persistence;

import it.unical.demacs.informatica.easyhomebackend.model.Recensione;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.ContattiDao;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.ImmobileDao;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.RecensioneDao;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.UserDao;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc.ContattiDaoJDBC;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc.ImmobileDaoJDBC;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc.RecensioneDaoJDBC;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc.UserDaoJDBC;
import lombok.Getter;

import java.sql.*;

public class DBManager {

    private UserDao userDao = null;
    private ImmobileDao immobileDao = null;
    private RecensioneDao recensioneDao = null;
    private ContattiDao contattiDao = null;
    private static DBManager instance;
    @Getter
    private Connection connection;

    private DBManager() {
        try {
            // Carica il driver JDBC (opzionale per le versioni più recenti di Java)
            Class.forName("org.postgresql.Driver");

            // Crea la connessione utilizzando i dettagli del database
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/EasyHome", // URL del database
                    "postgres", // Nome utente
                    "mirko" // Password
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante la connessione al database", e);
        }
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoJDBC(getConnection());
        }
        return userDao;
    }

    public ImmobileDao getImmobileDao() {
        if (immobileDao == null) {
            immobileDao = new ImmobileDaoJDBC(getConnection());
        }
        return immobileDao;
    }

    public RecensioneDao getRecensioneDao() {
        if (recensioneDao == null) {
            recensioneDao = new RecensioneDaoJDBC(getConnection());
        }
        return recensioneDao;
    }


    public ContattiDao getContattiDao() {
        if (contattiDao == null) {
            contattiDao = new ContattiDaoJDBC(getConnection());
        }
        return contattiDao;
    }
}
