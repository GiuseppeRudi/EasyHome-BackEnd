package it.unical.demacs.informatica.easyhomebackend.service;


import it.unical.demacs.informatica.easyhomebackend.model.Recensione;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.RecensioneDao;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IRecensioneService {
    Recensione Crearecensione(List<MultipartFile> foto, String nome, String descrizione, String tipo, int prezzo, int mq, int camere, int bagni, int anno, String etichetta, String posizione);
    
    Optional<Recensione> getImmobile(String id);
}
