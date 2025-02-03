package it.unical.demacs.informatica.easyhomebackend.service;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.model.ImmobileMinimal;
import it.unical.demacs.informatica.easyhomebackend.persistence.dto.ImmobileDto;
import it.unical.demacs.informatica.easyhomebackend.persistence.dto.MarkerDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IImmobileService {

        Immobile createImmobile(Immobile nuovoImmobile, List<MultipartFile> foto, String user) throws Exception;

        List<Immobile> getImmobiliFiltered(String tipo, String categoria, String provincia);

        Optional<Immobile> getImmobile(int id);

        List<ImmobileMinimal> getImmobiliFilteredMinimal(String tipo, String categoria, String provincia);

        List<MarkerDTO> getAllMarkers();

        void deleteImmobile(int id) throws Exception;
}


