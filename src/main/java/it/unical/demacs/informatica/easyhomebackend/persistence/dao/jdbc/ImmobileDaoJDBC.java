package it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.persistence.DBManager;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.ImmobileDao;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.*;
import java.util.ArrayList;
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

        List<MultipartFile> foto = new ArrayList<>();

        // Se hai una tabella immobile_foto per le foto
        String queryFoto = "SELECT foto FROM immagini WHERE immobile_id = ?";
        try (PreparedStatement statementFoto = connection.prepareStatement(queryFoto)) {
            statementFoto.setInt(1, resultSet.getInt("id"));
            ResultSet rsFoto = statementFoto.executeQuery();
            while (rsFoto.next()) {
                byte[] fotoBytes = rsFoto.getBytes("foto");

                // Converti byte[] in MultipartFile (questo passaggio è solo per simulare il file upload)
                // Solitamente, questa parte avviene solo sul lato client, ma qui è un esempio di come puoi farlo
                foto.add(new MultipartFile() {
                    @Override
                    public String getName() {
                        return "foto"; // Puoi impostare il nome del file o lasciarlo generico
                    }

                    @Override
                    public String getOriginalFilename() {
                        return "foto_" + System.currentTimeMillis(); // Nome del file temporaneo
                    }

                    @Override
                    public String getContentType() {
                        return "image/jpeg"; // Puoi determinare il tipo di contenuto dal file
                    }

                    @Override
                    public boolean isEmpty() {
                        return fotoBytes.length == 0;
                    }

                    @Override
                    public long getSize() {
                        return fotoBytes.length;
                    }

                    @Override
                    public byte[] getBytes() throws IOException {
                        return fotoBytes;
                    }

                    @Override
                    public InputStream getInputStream() throws IOException {
                        return new ByteArrayInputStream(fotoBytes);
                    }

                    @Override
                    public void transferTo(File dest) throws IOException, IllegalStateException {
                        Files.write(dest.toPath(), fotoBytes); // Salva il file temporaneamente
                    }
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il recupero delle foto", e);
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
                foto  // Adesso foto è una lista di MultipartFile
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
    public List<Immobile> findFiltered(String tipo, String categoria, String provincia) {
        List<Immobile> immobili = new ArrayList<>();

        // Query con parametri dinamici
        String query = "SELECT * FROM immobili WHERE tipo = ? AND categoria = ? AND provincia = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            // Imposta i parametri
            pstmt.setString(1, tipo);
            pstmt.setString(2, categoria);
            pstmt.setString(3, provincia);

            // Esegui la query
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Immobile immobile = mapRowToImmobile(rs);
                    immobili.add(immobile);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'esecuzione della query", e);
        }

        return immobili;
    }

    private Immobile mapRowToImmobile(ResultSet rs) throws SQLException {
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

        // Aggiungi altri campi necessari
        return immobile;
    }

    private void saveImmagini(int immobileId, List<MultipartFile> foto) throws SQLException, IOException {
        String queryFoto = "INSERT INTO immagini (immobile_id, foto) VALUES (?, ?)";
        try (PreparedStatement fotoStatement = connection.prepareStatement(queryFoto)) {
            for (MultipartFile file : foto) {
                fotoStatement.setInt(1, immobileId); // Associa l'immobile all'immagine
                fotoStatement.setBytes(2, file.getBytes()); // Converte l'immagine in byte[]
                fotoStatement.executeUpdate(); // Salva l'immagine nel database
            }
        }
    }

    @Override
    public void save(String nome, String tipo, String descrizione, String categoria, int prezzo, int mq, int camere, int bagni, int anno, String etichetta,String provincia, String indirizzo,List<MultipartFile> foto) {
        String queryImmobile = "INSERT INTO immobili (nome, tipo, descrizione, categoria, prezzo, mq, camere, bagni, anno, etichetta, provincia, latitudine, longitudine) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statementImmobile = connection.prepareStatement(queryImmobile, Statement.RETURN_GENERATED_KEYS)) {
            statementImmobile.setString(1, nome);
            statementImmobile.setString(2, tipo);
            statementImmobile.setString(3, descrizione);
            statementImmobile.setString(4, categoria);
            statementImmobile.setDouble(5, prezzo);
            statementImmobile.setInt(6, mq);
            statementImmobile.setInt(7, camere);
            statementImmobile.setInt(8, bagni);
            statementImmobile.setInt(9, anno);
            statementImmobile.setString(10, etichetta);
            statementImmobile.setString(11, provincia);
            statementImmobile.setDouble(12, 34.65475677);
            statementImmobile.setDouble(13, 56.56476563);

            // Esegui l'update dell'immobile e ottieni l'ID generato
            int affectedRows = statementImmobile.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserimento immobile fallito, nessuna riga modificata.");
            }

            // Recupera l'ID dell'immobile appena inserito
            try (ResultSet rs = statementImmobile.getGeneratedKeys()) {
                if (rs.next()) {
                    int immobileId = rs.getInt(1); // Recupera l'ID generato

                    // Salva le immagini
                    saveImmagini(immobileId, foto);
                } else {
                    throw new SQLException("Inserimento immobile fallito, nessun ID generato.");
                }
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il salvataggio dell'immobile", e);
        }
    }




}
