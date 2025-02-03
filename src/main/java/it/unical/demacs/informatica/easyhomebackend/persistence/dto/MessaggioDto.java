package it.unical.demacs.informatica.easyhomebackend.persistence.dto;

import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessaggioDto {
    private String oggetto;
    private String descrizione;
    private String acquirente;
    private int idImmobile;
}
