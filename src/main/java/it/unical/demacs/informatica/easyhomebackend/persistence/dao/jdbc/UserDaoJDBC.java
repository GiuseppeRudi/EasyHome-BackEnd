package it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc;



import it.unical.demacs.informatica.easyhomebackend.model.UserRole;
import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.ImmobileDao;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.UserDao;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc.proxy.UtenteProxy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJDBC  implements UserDao {

    Connection connection;
    private final ImmobileDao immobileDao;


    public UserDaoJDBC(Connection connection, ImmobileDao immobileDao) {
        this.connection = connection;
        this.immobileDao = immobileDao;
    }

    @Override
    public Utente findByPrimaryKey(String username) {
        String sql = "SELECT * FROM utente WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UtenteProxy(
                        rs.getString("username"),
                        rs.getString("password"),
                        UserRole.valueOf(rs.getString("role")),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("data_nascita"),
                        rs.getString("nazionalita"),
                        rs.getString("email"),
                        immobileDao
                );
            }
            throw new UsernameNotFoundException("Utente non trovato");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    public void update(Utente utente) {

    }

    @Override
    public void deleteByUsername(String username) {

    }
}