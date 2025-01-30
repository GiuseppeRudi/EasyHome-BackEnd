package it.unical.demacs.informatica.easyhomebackend.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.util.List;

@Getter
@Setter
@ToString
public class Immobile {
    private int id;
    private String nome;
    private String tipo;
    private String descrizione;
    private String categoria;
    private int prezzo;
    private int mq;
    private int camere;
    private int bagni;
    private int anno;
    private String etichetta;
    private String provincia;
    private double latitudine;
    private double longitudine;
    @Setter
    @Getter
    private List<MultipartFile>  foto;  // Modificato da String a byte[] per gestire i file

    // Costruttori, getter e setter
    public Immobile() {
    }

    public Immobile(int id, String nome, String tipo, String descrizione, String categoria, int prezzo, int mq, int camere, int bagni, int anno, String etichetta, String provincia, double latitudine, double longitudine, List<MultipartFile>  foto) {
        this.nome = nome;
        this.id = id;
        this.foto = foto;  // Assegna il file come byte array
        this.descrizione = descrizione;
        this.categoria=categoria;
        this.tipo = tipo;
        this.prezzo = prezzo;
        this.mq = mq;
        this.camere = camere;
        this.bagni = bagni;
        this.anno = anno;
        this.etichetta = etichetta;
        this.provincia = provincia;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }
}

