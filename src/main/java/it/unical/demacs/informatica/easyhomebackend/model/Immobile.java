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
    @Setter
    @Getter
    private List<MultipartFile>  foto;  // Modificato da String a byte[] per gestire i file
    private String descrizione;
    private String tipo;
    private int prezzo;
    private int mq;
    private int camere;
    private int bagni;
    private int anno;
    private String etichetta;
    private String posizione;

    // Costruttori, getter e setter
    public Immobile() {
    }

    public Immobile(int id, String nome, List<MultipartFile>  foto, String descrizione, String tipo, int prezzo, int mq, int camere, int bagni, int anno, String etichetta, String posizione) {
        this.nome = nome;
        this.id = id;
        this.foto = foto;  // Assegna il file come byte array
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.prezzo = prezzo;
        this.mq = mq;
        this.camere = camere;
        this.bagni = bagni;
        this.anno = anno;
        this.etichetta = etichetta;
        this.posizione = posizione;
    }

}

