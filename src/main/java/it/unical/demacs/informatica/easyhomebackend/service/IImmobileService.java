package it.unical.demacs.informatica.easyhomebackend.service;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IImmobileService {

        Immobile createImmobile(String nome, String tipo, String descrizione, String categoria, int prezzo, int mq, int camere, int bagni, int anno, String etichetta,String provincia, String indirizzo, List<MultipartFile> foto);

        List<Immobile> getImmobiliFiltered(String tipo, String categoria, String provincia);

        Optional<Immobile> getImmobile(String id);
    }


