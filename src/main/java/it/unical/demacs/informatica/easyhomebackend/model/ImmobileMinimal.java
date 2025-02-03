package it.unical.demacs.informatica.easyhomebackend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImmobileMinimal {
    private Integer id;
    private String nome;
    private int prezzo;
    private String tipo;
    private String categoria;
    private int mq;
    private String immagine; // Il primo elemento dell'array immagini

    public ImmobileMinimal(Integer id,String nome, int prezzo, String tipo, String categoria, int mq, String immagine) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.tipo = tipo;
        this.categoria = categoria;
        this.mq = mq;
        this.immagine = immagine;
    }

    // Getters e Setters
}
