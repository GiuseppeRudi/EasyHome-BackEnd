package it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.ImmobileDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImmobileDaoJDBC implements ImmobileDao {
    private final Connection connection;

    public ImmobileDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Immobile immobile) {
        String query = "INSERT INTO immobili (id, descrizione, tipo, prezzo, mq, camere, bagni, anno, posizione) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, immobile.getId());
            statement.setString(2, immobile.getDescrizione());
            statement.setString(3, immobile.getTipo());
            statement.setDouble(4, immobile.getPrezzo());
            statement.setInt(5, immobile.getMq());
            statement.setInt(6, immobile.getCamere());
            statement.setInt(7, immobile.getBagni());
            statement.setInt(8, immobile.getAnno());
            statement.setString(9, immobile.getPosizione());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante l'inserimento dell'immobile", e);
        }
    }

    @Override
    public Immobile findByPrimaryKey(String id) {
        String query = "SELECT * FROM immobili WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Immobile(
                        resultSet.getString("id"),
                        resultSet.getString("descrizione"),
                        resultSet.getString("tipo"),
                        resultSet.getDouble("prezzo"),
                        resultSet.getInt("mq"),
                        resultSet.getInt("camere"),
                        resultSet.getInt("bagni"),
                        resultSet.getInt("anno"),
                        resultSet.getString("posizione")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il recupero dell'immobile", e);
        }
        return null;
    }

    @Override
    public List<Immobile> findAll() {
        List<Immobile> immobili = new ArrayList<>();
        String query = "SELECT * FROM immobili";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Immobile immobile = new Immobile(
                        resultSet.getString("id"),
                        resultSet.getString("descrizione"),
                        resultSet.getString("tipo"),
                        resultSet.getDouble("prezzo"),
                        resultSet.getInt("mq"),
                        resultSet.getInt("camere"),
                        resultSet.getInt("bagni"),
                        resultSet.getInt("anno"),
                        resultSet.getString("posizione")
                );
                immobili.add(immobile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il recupero degli immobili", e);
        }
        return immobili;
    }
}
