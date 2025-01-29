package it.unical.demacs.informatica.easyhomebackend.service;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.persistence.DBManager;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.ImmobileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ImmobileService implements IImmobileService {

    private final ImmobileDao immobileDao=DBManager.getInstance().getImmobileDao();


    public ImmobileService() {
    }

    @Override
    public Immobile createImmobile(List<byte[]> foto, String nome, String descrizione, String tipo, Double prezzo, Integer mq, Integer camere, Integer bagni, Integer anno, String etichetta, String posizione) {
        // Validazione dei campi obbligatori
        if (nome == null || descrizione == null || prezzo <= 0) {
            throw new IllegalArgumentException("I dati dell'immobile non sono validi");
        }

        try {
            // Salvataggio dell'immobile
            this.immobileDao.save(foto,nome,descrizione,tipo,prezzo,mq,camere,bagni,anno,etichetta,posizione);
        } catch (Exception e) {
            // Gestione delle eccezioni
            e.printStackTrace();
            throw new RuntimeException("Errore durante il salvataggio dell'immobile", e);
        }

        // Recupera e restituisce l'immobile appena creato
        return this.getImmobile(nome).orElseThrow(() -> new RuntimeException("Immobile non trovato dopo il salvataggio"));
    }

    @Override
    public List<Immobile> getAllImmobili() {
        try {
            return this.immobileDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il recupero degli immobili", e);
        }
    }

    @Override
    public List<Immobile> getImmobiliFiltered(String tipo, String affittoVendita, String luogo) {
        // Supponendo che tu stia usando JDBC o un repository, puoi costruire una query dinamica:
        return immobileDao.findFiltered(tipo, affittoVendita, luogo);
    }


    @Override
    public Optional<Immobile> getImmobile(String id) {
        try {
            Immobile immobile = immobileDao.findByPrimaryKey(id);
            return Optional.ofNullable(immobile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il recupero dell'immobile", e);
        }
    }
}
