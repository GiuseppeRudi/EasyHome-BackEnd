package it.unical.demacs.informatica.easyhomebackend.controller;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.service.IImmobileService;
import it.unical.demacs.informatica.easyhomebackend.service.ImmobileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ImmobileController {

    private final IImmobileService immobileService= new ImmobileService();

    public ImmobileController() { }

    @RequestMapping(value = "/auth/immobili/createImmobile", method = RequestMethod.POST)
    public ResponseEntity<Void> createImmobile(
            @RequestParam("nome") String nome,
            @RequestParam("tipo") String tipo,
            @RequestParam("descrizione") String descrizione,
            @RequestParam("categoria") String categoria,
            @RequestParam("prezzo") int prezzo,
            @RequestParam("mq") int mq,
            @RequestParam("camere") int camere,
            @RequestParam("bagni") int bagni,
            @RequestParam("anno") int anno,
            @RequestParam("etichetta") String etichetta,
            @RequestParam("provincia") String provincia,
            @RequestParam("indirizzo") String indirizzo,
            @RequestParam(value = "foto", required = false) List<MultipartFile> foto) {

        this.immobileService.createImmobile(nome,tipo,descrizione,categoria,prezzo,mq,camere,bagni,anno,etichetta, provincia, indirizzo,foto);
        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "/open/immobili", method = RequestMethod.GET)
    public ResponseEntity<List<Immobile>> getImmobili(
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "categoria", required = false) String categoria,
            @RequestParam(value = "provincia", required = false) String provincia) {

        // Utilizza i parametri per filtrare i risultati
        List<Immobile> immobili = this.immobileService.getImmobiliFiltered(tipo, categoria, provincia);
        return ResponseEntity.ok(immobili);
    }


}
