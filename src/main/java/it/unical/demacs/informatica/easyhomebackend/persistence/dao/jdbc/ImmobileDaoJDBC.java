package it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.model.ImmobileMinimal;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.ImmobileDao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ImmobileDaoJDBC implements ImmobileDao {
    private final Connection connection;

    public ImmobileDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Immobile findByPrimaryKey(int id) {
        String query = "SELECT * FROM immobili WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
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

        Array array = resultSet.getArray("immagini");
        List<String> immagini = new ArrayList<>();
        if (array != null) {
            Collections.addAll(immagini, (String[]) array.getArray());
        }

        // Creazione dell'oggetto Immobile con foto convertite
        return new Immobile(
                resultSet.getInt("id"),
                resultSet.getString("nome"),
                resultSet.getString("tipo"),
                resultSet.getString("descrizione"),
                resultSet.getString("categoria"),
                resultSet.getInt("prezzo"),
                resultSet.getInt("mq"),
                resultSet.getInt("camere"),
                resultSet.getInt("bagni"),
                resultSet.getInt("anno"),
                resultSet.getString("etichetta"),
                resultSet.getString("provincia"),
                resultSet.getDouble("latitudine"),
                resultSet.getDouble("longitudine"),
                immagini
        );
    }


    @Override
    public List<Immobile> findAll() {
        List<Immobile> immobili = new ArrayList<>();
        String query = "SELECT * FROM immobile";

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
    public List<Immobile> findFiltered(String tipo, String categoria, String provincia) {
        List<Immobile> immobili = new ArrayList<>();


        String query = "SELECT * FROM immobili";

        List<String> conditions = new ArrayList<>();

        if (!Objects.equals(tipo, "Tutti")) conditions.add("tipo = ?");
        if (!Objects.equals(categoria, "Tutti")) conditions.add("categoria = ?");
        if (!Objects.equals(provincia, "Tutte")) conditions.add("provincia = ?");

        if (!conditions.isEmpty()) {
            query += " WHERE " + String.join(" AND ", conditions);
        }
        System.out.println(query);

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            int paramIndex = 1;
            if (!Objects.equals(tipo, "Tutti")) {
                pstmt.setString(paramIndex++, tipo);
            }
            if (!Objects.equals(categoria, "Tutti")) {
                pstmt.setString(paramIndex++, categoria);
            }
            if (!Objects.equals(provincia, "Tutte")) {
                pstmt.setString(paramIndex, provincia);
            }

            // Esegui la query
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Immobile immobile = mapRowToImmobile(rs);
                    immobili.add(immobile);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Errore durante l'esecuzione della query", e);
        }

        return immobili;
    }

    private Immobile mapRowToImmobile(ResultSet rs) throws Exception {
        Immobile immobile = new Immobile();
        immobile.setId(rs.getInt("id"));
        immobile.setNome(rs.getString("nome"));
        immobile.setTipo(rs.getString("tipo"));
        immobile.setDescrizione(rs.getString("descrizione"));
        immobile.setCategoria(rs.getString("categoria"));
        immobile.setPrezzo(rs.getInt("prezzo"));
        immobile.setMq(rs.getInt("mq"));
        immobile.setCamere(rs.getInt("camere"));
        immobile.setBagni(rs.getInt("bagni"));
        immobile.setAnno(rs.getInt("anno"));
        immobile.setEtichetta(rs.getString("etichetta"));
        immobile.setProvincia(rs.getString("provincia"));
        immobile.setLatitudine(rs.getDouble("latitudine"));
        immobile.setLongitudine(rs.getDouble("longitudine"));
        immobile.setFoto(getImmagini(immobile.getId()));

        return immobile;
    }

    @Override
    public List<byte[]> getImmagini(Integer id) throws Exception {
        Immobile immobile = findByPrimaryKey(id);
        List<String> immobiliFotoPath = immobile.getFotoPaths();
        List<byte[]> immobiliFoto = new ArrayList<>();
        for (String path : immobiliFotoPath) {
            Path p = Path.of(path);
            immobiliFoto.add(Files.readAllBytes(p));
        }
        return immobiliFoto;
    }

    @Override
    public void save(Immobile immobile, String user) {
        String queryImmobile = "INSERT INTO immobili (id, nome, tipo, descrizione, categoria, prezzo, mq, camere, bagni, anno, etichetta, provincia, latitudine, longitudine, immagini, venditore) " +
                "VALUES (COALESCE(?, nextval('immobili_id_seq')),?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT (id) DO UPDATE SET " +
                "nome=EXCLUDED.nome, " +
                "tipo=EXCLUDED.tipo, " +
                "descrizione=EXCLUDED.descrizione, " +
                "categoria=EXCLUDED.categoria, " +
                "prezzo=EXCLUDED.prezzo, " +
                "mq=EXCLUDED.mq, " +
                "camere=EXCLUDED.camere, " +
                "bagni=EXCLUDED.bagni, " +
                "anno=EXCLUDED.anno, " +
                "etichetta=EXCLUDED.etichetta, " +
                "provincia=EXCLUDED.provincia, " +
                "latitudine=EXCLUDED.latitudine, " +
                "longitudine=EXCLUDED.longitudine, " +
                "immagini=EXCLUDED.immagini, " +
                "venditore=EXCLUDED.venditore " +
                "RETURNING id";
        try (PreparedStatement statementImmobile = connection.prepareStatement(queryImmobile, Statement.RETURN_GENERATED_KEYS)) {
            if (immobile.getId() != null) {
                statementImmobile.setObject(1, immobile.getId());
            } else {
                statementImmobile.setNull(1, Types.INTEGER);
            }
            statementImmobile.setString(2, immobile.getNome());
            statementImmobile.setString(3, immobile.getTipo());
            statementImmobile.setString(4, immobile.getDescrizione());
            statementImmobile.setString(5, immobile.getCategoria());
            statementImmobile.setDouble(6, immobile.getPrezzo());
            statementImmobile.setInt(7, immobile.getMq());
            statementImmobile.setInt(8, immobile.getCamere());
            statementImmobile.setInt(9, immobile.getBagni());
            statementImmobile.setInt(10, immobile.getAnno());
            statementImmobile.setString(11, immobile.getEtichetta());
            statementImmobile.setString(12, immobile.getProvincia());
            statementImmobile.setDouble(13, immobile.getLatitudine());
            statementImmobile.setDouble(14, immobile.getLongitudine());
            List<String> imagesPaths = immobile.getFotoPaths();
            Array sqlArray = connection.createArrayOf("text", imagesPaths.toArray());
            statementImmobile.setArray(15, sqlArray);
            statementImmobile.setString(16, user);
            // Esegui l'update dell'immobile e ottieni l'ID generato
            int affectedRows = statementImmobile.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserimento immobile fallito, nessuna riga modificata.");
            }

            // Recupera l'ID dell'immobile appena inserito
            try (ResultSet rs = statementImmobile.getGeneratedKeys()) {
                if (rs.next()) {
                    immobile.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Inserimento immobile fallito, nessun ID generato.");
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il salvataggio dell'immobile", e);
        }
    }

    @Override
    public List<Immobile> findByUserId(String username) {
        String sql = "SELECT * FROM immobili WHERE venditore = ?";
        List<Immobile> immobili = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Immobile immobile = createImm(rs);
                immobile.setFoto(getImmagini(rs.getInt("id")));
                immobili.add(immobile);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return immobili;
    }


    @Override
    public List<ImmobileMinimal> getImmobiliFilteredMinimal(String tipo, String categoria, String provincia) {
        List<ImmobileMinimal> immobiliMinimal = new ArrayList<>();

        String query = "SELECT nome, prezzo, tipo, categoria, mq, " +
                "CASE WHEN array_length(immagini, 1) > 0 THEN immagini[1] ELSE NULL END AS immagine " +
                "FROM immobili";

        List<String> conditions = new ArrayList<>();

        if (!Objects.equals(tipo, "Tutti")) conditions.add("tipo = ?");
        if (!Objects.equals(categoria, "Tutti")) conditions.add("categoria = ?");
        if (!Objects.equals(provincia, "Tutte")) conditions.add("provincia = ?");

        if (!conditions.isEmpty()) {
            query += " WHERE " + String.join(" AND ", conditions);
        }

        System.out.println("SQL Query: " + query);

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            int paramIndex = 1;
            if (!Objects.equals(tipo, "Tutti")) {
                pstmt.setString(paramIndex++, tipo);
            }
            if (!Objects.equals(categoria, "Tutti")) {
                pstmt.setString(paramIndex++, categoria);
            }
            if (!Objects.equals(provincia, "Tutte")) {
                pstmt.setString(paramIndex++, provincia);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String nome = rs.getString("nome");
                    int prezzo = rs.getInt("prezzo");
                    String tipoImmobile = rs.getString("tipo");
                    String categoriaImmobile = rs.getString("categoria");
                    int mq = rs.getInt("mq");
                    String immagine = rs.getString("immagine");

                    immobiliMinimal.add(new ImmobileMinimal(nome, prezzo, tipoImmobile, categoriaImmobile, mq, immagine));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'esecuzione della query", e);
        }

        return immobiliMinimal;
    }



}
