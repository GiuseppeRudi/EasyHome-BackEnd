package it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc;



import it.unical.demacs.informatica.easyhomebackend.model.UserRole;
import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBC  implements UserDao {


    Connection connection;


    public UserDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Utente findByPrimaryKey(String username) {

        String query = "SELECT username, password , role, nome, cognome, data_nascita, nazionalita, email FROM utente WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Utente(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        UserRole.valueOf(resultSet.getString("role")),
                        resultSet.getString("nome"),
                        resultSet.getString("cognome"),
                        resultSet.getString("data_nascita"),
                        resultSet.getString("nazionalita"),
                        resultSet.getString("email")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void save(Utente utente) {


        String query = "INSERT INTO utente (username, password , role,nome,cognome, data_nascita, nazionalita, email) VALUES (?, ? , ?, ?, ?, ?, ?,?) " ;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, utente.getUsername());
            statement.setString(2, utente.getPassword());
            statement.setString(3, utente.getRole().toString());
            statement.setString(4, utente.getNome());
            statement.setString(5, utente.getCognome());
            statement.setString(6, utente.getData_nascita());
            statement.setString(7, utente.getNazionalita());
            statement.setString(8, utente.getEmail());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<String> findAllUsernames() {
        String sql = "SELECT username FROM utente";  // Query per ottenere tutti gli username
        List<String> usernames = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            // Elenco di username dalla risposta del database
            while (resultSet.next()) {
                usernames.add(resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usernames;
    }
}