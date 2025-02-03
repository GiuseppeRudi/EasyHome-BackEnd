package it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc;

import it.unical.demacs.informatica.easyhomebackend.model.Recensione;
import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.persistence.DBManager;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.RecensioneDao;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.UserDao;

import java.sql.*;

public class RecensioneDaoJDBC implements RecensioneDao {
    private final Connection connection;

    public RecensioneDaoJDBC(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void save(Recensione recensione) {
        String query = "INSERT INTO recensione (id, rating, descrizione, acquirente, idImmobile) " +
                "VALUES (COALESCE(?, nextval('recensione_id_seq')), ?, ?, ?, ?) ";
        try (PreparedStatement s = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            if (recensione.getId() != null) {
                s.setObject(1, recensione.getId());
            } else {
                s.setNull(1, Types.INTEGER);
            }

            s.setInt(2, recensione.getRating());
            s.setString(3, recensione.getDescrizione());
            s.setString(4, recensione.getAcquirente());
            s.setInt(5, recensione.getIdImmobile());

            s.executeUpdate();

            try (ResultSet rs = s.getGeneratedKeys()) {
                if (rs.next()) {
                    recensione.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Inserimento messaggio fallito, nessun ID generato.");
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il salvataggio del messaggio", e);
        }
    }
}
