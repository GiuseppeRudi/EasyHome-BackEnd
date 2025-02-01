package it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc;



import it.unical.demacs.informatica.easyhomebackend.model.UserRole;
import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.UserDao;
import it.unical.demacs.informatica.easyhomebackend.persistence.dto.UserRoleDto;

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