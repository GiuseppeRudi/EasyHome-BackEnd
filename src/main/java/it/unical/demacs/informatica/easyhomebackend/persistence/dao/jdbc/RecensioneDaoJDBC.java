package it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc;

import it.unical.demacs.informatica.easyhomebackend.model.Recensione;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.RecensioneDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecensioneDaoJDBC implements RecensioneDao {
    private final Connection connection;

    public RecensioneDaoJDBC(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void save(Recensione recensione) {
        String query = "INSERT INTO Recensione (rating, descrizione) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, recensione.getRating());  // Corretto getRating()
            statement.setString(2, recensione.getDescrizione());  // Corretto getDescrizione()

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
