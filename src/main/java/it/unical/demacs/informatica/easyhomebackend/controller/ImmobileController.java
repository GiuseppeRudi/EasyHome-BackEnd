package it.unical.demacs.informatica.easyhomebackend.controller;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.service.IImmobileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ImmobileController {

    private final IImmobileService immobileService;

    public ImmobileController(IImmobileService immobileService) {
        this.immobileService = immobileService;
    }

    @RequestMapping(value = "/auth/immobili/createImmobile", method = RequestMethod.POST)
    public ResponseEntity<Void> createImmobile(
            @RequestParam("foto") List<byte[]>  foto,
            @RequestParam("nome") String nome,
            @RequestParam("descrizione") String descrizione,
            @RequestParam("tipo") String tipo,
            @RequestParam("prezzo") Double prezzo,
            @RequestParam("mq") Integer mq,
            @RequestParam("camere") Integer camere,
            @RequestParam("bagni") Integer bagni,
            @RequestParam("anno") Integer anno,
            @RequestParam("etichetta") String etichetta,
            @RequestParam("posizione") String posizione) {

        this.immobileService.createImmobile(foto,nome,descrizione,tipo,prezzo,mq,camere,bagni,anno,etichetta,posizione);
        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "/open/immobili", method = RequestMethod.GET)
    public ResponseEntity<List<Immobile>> getImmobili(
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "affittoVendita", required = false) String affittoVendita,
            @RequestParam(value = "luogo", required = false) String luogo) {

        // Utilizza i parametri per filtrare i risultati
        List<Immobile> immobili = this.immobileService.getImmobiliFiltered(tipo, affittoVendita, luogo);
        return ResponseEntity.ok(immobili);
    }


}
