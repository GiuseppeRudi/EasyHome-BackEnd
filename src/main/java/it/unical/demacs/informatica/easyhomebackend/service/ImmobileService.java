package it.unical.demacs.informatica.easyhomebackend.service;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.persistence.DBManager;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.ImmobileDao;
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
    public Immobile createImmobile(String nome, String tipo, String descrizione, String categoria, int prezzo, int mq, int camere, int bagni, int anno, String etichetta, String indirizzo,List<MultipartFile> foto){
        // Validazione dei campi obbligatori

        if (nome == null || prezzo <= 0) {
            throw new IllegalArgumentException("I dati dell'immobile non sono validi");
        }

        try {
            // Salvataggio dell'immobile
            this.immobileDao.save(nome,tipo,descrizione,categoria,prezzo,mq,camere,bagni,anno,etichetta,indirizzo,foto);
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
