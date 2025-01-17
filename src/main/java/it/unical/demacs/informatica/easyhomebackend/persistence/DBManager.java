package it.unical.demacs.informatica.easyhomebackend.persistence;

import it.unical.demacs.informatica.easyhomebackend.persistence.dao.UtenteDAO;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc.UtenteDaoJDBC;

import java.sql.*;

public class DBManager {

    private static UtenteDAO utentedao;
    private static DBManager instance;
    private Connection connection;

    private DBManager() {
        try {
            // Carica il driver JDBC (opzionale per le versioni più recenti di Java)
            Class.forName("org.postgresql.Driver");

            // Crea la connessione utilizzando i dettagli del database
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/EasyHome", // URL del database
                    "postgres", // Nome utente
                    "rudi" // Password
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante la connessione al database", e);
        }
    }

    public UtenteDAO getUtenteDao()
    {

        if (utentedao==null)
        {
            return new UtenteDaoJDBC();
        }
        
        return utentedao;
    }
    
    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }



    public static void main(String[] args) {
        // Ottieni l'istanza del DBManager
        DBManager dbManager = DBManager.getInstance();

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
