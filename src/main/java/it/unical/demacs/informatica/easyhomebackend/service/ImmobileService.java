package it.unical.demacs.informatica.easyhomebackend.service;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.model.ImmobileMinimal;
import it.unical.demacs.informatica.easyhomebackend.persistence.DBManager;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.ImmobileDao;
import it.unical.demacs.informatica.easyhomebackend.persistence.dto.MarkerDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImmobileService implements IImmobileService {

    private final ImmobileDao immobileDao=DBManager.getInstance().getImmobileDao();


    public ImmobileService() {
    }

    @Override
    public Immobile createImmobile(Immobile immobile, List<MultipartFile> foto, String user) throws Exception {
        // Validazione dei campi obbligatori

        if (immobile.getNome() == null || immobile.getPrezzo() <= 0) {
            throw new IllegalArgumentException("I dati dell'immobile non sono validi");
        }
        try {
            // Salvataggio dell'immobile
            this.immobileDao.save(immobile,user);
        } catch (Exception e) {
            // Gestione delle eccezioni
            e.printStackTrace();
            throw new RuntimeException("Errore durante il salvataggio dell'immobile", e);
        }
        Optional<Immobile> savedImmobile = getImmobile(immobile.getId());
        if (savedImmobile.isPresent()) {
            saveImmagini(savedImmobile.get(), foto);
            immobileDao.save(savedImmobile.get(), user);
        }

        // Recupera e restituisce l'immobile appena creato
        return savedImmobile.orElseThrow(() -> new RuntimeException("Immobile non trovato dopo il salvataggio"));
    }

    private static final String immobiliImagesDir = "immobiliImages/";

    private void saveImmagini(Immobile immobile, List<MultipartFile> foto) throws Exception {
        int immobileDir = immobile.getId();
        File directory = new File(immobiliImagesDir + immobileDir);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new Exception("Could not create directory");
        }
        deleteExistingFiles(directory.listFiles());
        List<String> immagini = new ArrayList<>();
        for (MultipartFile f : foto) {
            String fileName = f.getOriginalFilename();
            Path path = Path.of(immobiliImagesDir + immobileDir, fileName);
            Files.copy(f.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            immagini.add(immobiliImagesDir + immobileDir + "/" + fileName);
        }
        immobile.setFotoPaths(immagini);
    }

    private void deleteExistingFiles(File[] existingFiles) throws Exception {
        if (existingFiles != null) {
            for (File existingFile : existingFiles) {
                if (!existingFile.delete()) {
                    throw new Exception("Could not delete existing file: " + existingFile.getName());
                }
            }
        }
    }

    @Override
    public List<Immobile> getImmobiliFiltered(String tipo, String categoria, String provincia) {
        return immobileDao.findFiltered(tipo, categoria, provincia);
    }


    @Override
    public Optional<Immobile> getImmobile(int id) {
        try {
            Immobile immobile = immobileDao.findByPrimaryKey(id);
            return Optional.ofNullable(immobile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il recupero dell'immobile", e);
        }
    }

    @Override
    public List<ImmobileMinimal> getImmobiliFilteredMinimal(String tipo, String categoria, String provincia) {
        return immobileDao.getImmobiliFilteredMinimal(tipo, categoria, provincia);
    }


    public List<MarkerDTO> getAllMarkers() {
        return immobileDao.findAll()
                .stream()
                .map(immobile -> new MarkerDTO(immobile.getLatitudine(), immobile.getLongitudine()))
                .collect(Collectors.toList());
    }

    @Override
    public Immobile getImmobileById(int id) {
        Optional<Immobile> immobile = immobileDao.findById(id);
        return immobile.orElse(null);
    }


}
