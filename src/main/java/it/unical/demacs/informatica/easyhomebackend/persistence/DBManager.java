package it.unical.demacs.informatica.easyhomebackend.persistence;

import it.unical.demacs.informatica.easyhomebackend.persistence.dao.ImmobileDao;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.UserDao;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc.ImmobileDaoJDBC;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc.UserDaoJDBC;
import lombok.Getter;

import java.sql.*;

public class DBManager {

    private UserDao userDao = null;
    private ImmobileDao immobileDao = null;
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
                    "postgres" // Password
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

    public static void main(String[] args) {
        // Ottieni l'istanza del DBManager
        DBManager dbManager = DBManager.getInstance();

        // Esegui una query di esempio su "utente"
        try (Connection con = dbManager.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM utente")) {

            // Stampa i risultati della query
            while (rs.next()) {
                System.out.println("ID: " + rs.getLong("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("---------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante l'esecuzione della query", e);
        }
    }
}
