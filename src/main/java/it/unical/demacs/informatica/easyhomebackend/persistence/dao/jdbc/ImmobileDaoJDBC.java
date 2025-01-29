package it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.ImmobileDao;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImmobileDaoJDBC implements ImmobileDao {
    private final Connection connection;

    public ImmobileDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Immobile findByPrimaryKey(String nome) {
        String query = "SELECT * FROM immobili WHERE nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nome);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return createImm(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il recupero dell'immobile", e);
        }
        return null;
    }

    private Immobile createImm(ResultSet resultSet) throws SQLException {


        List<byte[]> foto = new ArrayList<>();
        // Se hai una tabella immobile_foto per le foto
        String queryFoto = "SELECT foto FROM immobile_foto WHERE immobile_id = ?";
        try (PreparedStatement statementFoto = connection.prepareStatement(queryFoto)) {
            statementFoto.setInt(1, resultSet.getInt("id"));
            ResultSet rsFoto = statementFoto.executeQuery();
            while (rsFoto.next()) {
                foto.add(rsFoto.getBytes("foto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il recupero delle foto", e);
        }
        return new Immobile(
                resultSet.getInt("id"),
                resultSet.getString("nome"),
                foto,  // Qui è solo per un file, dovresti adattarlo per più file
                resultSet.getString("descrizione"),
                resultSet.getString("tipo"),
                resultSet.getDouble("prezzo"),
                resultSet.getInt("mq"),
                resultSet.getInt("camere"),
                resultSet.getInt("bagni"),
                resultSet.getInt("anno"),
                resultSet.getString("etichetta"),
                resultSet.getString("posizione")
        );
    }

    @Override
    public List<Immobile> findAll() {
        List<Immobile> immobili = new ArrayList<>();
        String query = "SELECT * FROM immobili";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Immobile immobile = createImm(resultSet);
                immobili.add(immobile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il recupero degli immobili", e);
        }
        return immobili;
    }

    @Override
    public List<Immobile> findFiltered(String tipo, String affittoVendita, String luogo) {
        List<Immobile> immobili = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM immobili WHERE 1=1");

        // Costruzione dinamica della query con i parametri
        if (tipo != null && !tipo.isEmpty()) {
            query.append(" AND tipo = ?");
        }
        if (affittoVendita != null && !affittoVendita.isEmpty()) {
            query.append(" AND affitto_vendita = ?");
        }
        if (luogo != null && !luogo.isEmpty()) {
            query.append(" AND luogo = ?");
        }

        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            int index = 1;

            // Impostazione dei parametri dinamici
            if (tipo != null && !tipo.isEmpty()) {
                statement.setString(index++, tipo);
            }
            if (affittoVendita != null && !affittoVendita.isEmpty()) {
                statement.setString(index++, affittoVendita);
            }
            if (luogo != null && !luogo.isEmpty()) {
                statement.setString(index++, luogo);
            }

            // Esecuzione della query
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Immobile immobile = createImm(resultSet);
                    immobili.add(immobile);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante la ricerca filtrata degli immobili", e);
        }

        return immobili;
    }

    @Override

    public void save(List<byte[]> foto, String nome, String descrizione, String tipo, Double prezzo, Integer mq, Integer camere, Integer bagni, Integer anno, String etichetta, String posizione) {
        String queryImmobile = "INSERT INTO immobili (nome, descrizione, tipo, prezzo, mq, camere, bagni, anno, etichetta, posizione) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statementImmobile = connection.prepareStatement(queryImmobile, Statement.RETURN_GENERATED_KEYS)) {
            statementImmobile.setString(1, nome);
            statementImmobile.setString(2, descrizione);
            statementImmobile.setString(3, tipo);
            statementImmobile.setDouble(4, prezzo);
            statementImmobile.setInt(5, mq);
            statementImmobile.setInt(6, camere);
            statementImmobile.setInt(7, bagni);
            statementImmobile.setInt(8, anno);
            statementImmobile.setString(9, etichetta);
            statementImmobile.setString(10, posizione);
            statementImmobile.executeUpdate();

            // Recupera l'ID dell'immobile appena inserito


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il salvataggio dell'immobile", e);
        }
    }


    // Metodo per convertire la lista di MultipartFile in array di byte
    private List<byte[]> convertToByteArrays(List<byte[]> files) throws SQLException {
        List<byte[]> byteArrayList = new ArrayList<>();
        byteArrayList.addAll(files);
        return byteArrayList;
    }
}
