package it.unical.demacs.informatica.easyhomebackend.service;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IImmobileService {

        Immobile createImmobile(List<byte[]>  foto,String nome,String descrizione,String tipo,Double prezzo,Integer mq,Integer camere,Integer bagni,Integer anno,String etichetta,String posizione);

        List<Immobile> getImmobiliFiltered(String tipo, String affittoVendita, String luogo);

        Optional<Immobile> getImmobile(String id);

        List<Immobile> getAllImmobili(); // Metodo per ottenere tutti gli immobili
    }


