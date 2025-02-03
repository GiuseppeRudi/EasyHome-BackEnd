package it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc;

import it.unical.demacs.informatica.easyhomebackend.model.Messaggio;
import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.persistence.DBManager;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.MessaggioDao;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.UserDao;

import java.sql.*;

public class MessaggioDaoJDBC implements MessaggioDao {
    private final Connection connection;
    public MessaggioDaoJDBC(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void save(Messaggio messaggio,String acquirente,int immobileId) {
        String query = "INSERT INTO messaggio (id, oggetto, descrizione, acquirente, email, telefono, idImmobile) " +
                "VALUES (COALESCE(?, nextval('messaggio_id_seq')), ?, ?, ?, ?, ?, ?) ";
        try (PreparedStatement s = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            if (messaggio.getId() != null) {
                s.setObject(1, messaggio.getId());
            } else {
                s.setNull(1, Types.INTEGER);
            }

            s.setString(2, messaggio.getOggetto());
            s.setString(3, messaggio.getDescrizione());
            s.setString(4, acquirente);
            UserDao utenteDao = DBManager.getInstance().getUserDao();
            Utente utente = utenteDao.findByPrimaryKey(acquirente);
            s.setString(5, utente.getEmail());
            s.setInt(6, Integer.parseInt(utente.getPhoneNumber()));
            s.setInt(7, immobileId);

            s.executeUpdate();

            try (ResultSet rs = s.getGeneratedKeys()) {
                if (rs.next()) {
                    messaggio.setId(rs.getInt(1));
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
