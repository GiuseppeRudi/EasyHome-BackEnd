package it.unical.demacs.informatica.easyhomebackend.service;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;

import java.util.List;
import java.util.Optional;

public interface IImmobileService {

        Immobile createImmobile(Immobile immobile);

        Optional<Immobile> getImmobile(String id);

        List<Immobile> getAllImmobili(); // Metodo per ottenere tutti gli immobili
    }


