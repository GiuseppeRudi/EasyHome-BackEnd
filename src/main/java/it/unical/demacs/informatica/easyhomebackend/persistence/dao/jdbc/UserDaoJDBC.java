package it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc;



import it.unical.demacs.informatica.easyhomebackend.model.UserRole;
import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.ImmobileDao;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.UserDao;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc.proxy.UtenteProxy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import it.unical.demacs.informatica.easyhomebackend.persistence.dto.UserRoleDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public void delete(Utente utente) {
        String query = "DELETE FROM utente WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, utente.getUsername());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("Nessun utente trovato con username: " + utente.getUsername());
            } else {
                System.out.println("Utente con username: " + utente.getUsername() + " eliminato con successo.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<UserRoleDto> findAllUsernamesAndRoles() {
        String sql = "SELECT username, role FROM utente";  // Query per ottenere username e ruolo
        List<UserRoleDto> userList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            // Elenco di username e ruolo dalla risposta del database
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String role = resultSet.getString("role");
                userList.add(new UserRoleDto(username, UserRole.valueOf(role)));  // Crea oggetti UserRoleDto
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void changeUserRole(String username, UserRole newRole) {
        String sql = "UPDATE utente SET role = ? WHERE username = ?"; // Query per aggiornare il ruolo

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newRole.name()); // Converte l'enum UserRole in stringa
            statement.setString(2, username);

            int rowsUpdated = statement.executeUpdate(); // Esegue l'aggiornamento

            if (rowsUpdated == 0) {
                System.out.println("Nessun utente trovato con username: " + username);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}