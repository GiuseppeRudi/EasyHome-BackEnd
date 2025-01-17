package it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc;

import it.unical.demacs.informatica.easyhomebackend.persistence.DBManager;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.UtenteDAO;
import it.unical.demacs.informatica.easyhomebackend.persistence.model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtenteDaoJDBC implements UtenteDAO {

    private final Connection conn  = DBManager.getInstance().getConnection();


    @Override
    public List<Utente> findAll() {
        String query = "SELECT id, nome, email FROM utente";
        List<Utente> utenti = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Utente utente = new Utente();
                utente.setId(rs.getLong("id"));
                utente.setNome(rs.getString("nome"));
                utente.setEmail(rs.getString("email"));
                utenti.add(utente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utenti;
    }

    @Override
    public void save(Utente utente) {
        String query = "INSERT INTO utente (nome, email) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, utente.getNome());
            stmt.setString(2, utente.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
