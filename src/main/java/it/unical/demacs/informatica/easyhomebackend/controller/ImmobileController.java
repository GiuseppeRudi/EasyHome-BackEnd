package it.unical.demacs.informatica.easyhomebackend.controller;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.service.IImmobileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ImmobileController {

    private final IImmobileService immobileService;

    public ImmobileController(IImmobileService immobileService) {
        this.immobileService = immobileService;
    }

    @RequestMapping(value = "/auth/immobili/createImmobile", method = RequestMethod.POST)
    public ResponseEntity<Void> createImmobile(@RequestBody Immobile immobile) {
        this.immobileService.createImmobile(immobile);
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
