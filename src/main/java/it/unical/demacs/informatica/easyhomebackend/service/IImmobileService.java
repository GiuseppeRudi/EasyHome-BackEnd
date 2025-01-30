package it.unical.demacs.informatica.easyhomebackend.service;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IImmobileService {

        Immobile createImmobile(List<MultipartFile> foto, String nome, String descrizione, String tipo, int prezzo, int mq, int camere, int bagni, int anno, String etichetta, String posizione);

        List<Immobile> getImmobiliFiltered(String tipo, String affittoVendita, String luogo);

        Optional<Immobile> getImmobile(String id);

        List<Immobile> getAllImmobili(); // Metodo per ottenere tutti gli immobili
    }


