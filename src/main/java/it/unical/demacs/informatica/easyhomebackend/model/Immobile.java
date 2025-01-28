package it.unical.demacs.informatica.easyhomebackend.model;

import lombok.*;




@Getter
@Setter
@ToString
public class Immobile {
    private String id;
    private String descrizione;
    private String tipo;
    private double prezzo;
    private int mq;
    private int camere;
    private int bagni;
    private int anno;
    private String posizione;

    // Costruttori, getter e setter
    public Immobile() {
    }

    public Immobile(String id, String descrizione, String tipo, double prezzo, int mq, int camere, int bagni, int anno, String posizione) {
        this.id = id;
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.prezzo = prezzo;
        this.mq = mq;
        this.camere = camere;
        this.bagni = bagni;
        this.anno = anno;
        this.posizione = posizione;
    }

    // Getter e setter
}
