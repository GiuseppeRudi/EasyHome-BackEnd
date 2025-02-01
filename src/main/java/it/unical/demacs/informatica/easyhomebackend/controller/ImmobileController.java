package it.unical.demacs.informatica.easyhomebackend.controller;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.persistence.dto.ImmobileDto;
import it.unical.demacs.informatica.easyhomebackend.service.IImmobileService;
import it.unical.demacs.informatica.easyhomebackend.service.ImmobileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ImmobileController {

    private final IImmobileService immobileService= new ImmobileService();

    public ImmobileController() { }

    @RequestMapping(value = "/auth/immobili/createImmobile", method = RequestMethod.POST)
    public ResponseEntity<Void> createImmobile(
            @ModelAttribute ImmobileDto immobileDto) throws Exception {
        Immobile nuovoImmobile = new Immobile();
        nuovoImmobile.setId(null);
        nuovoImmobile.setNome(immobileDto.getNome());
        nuovoImmobile.setTipo(immobileDto.getTipo());
        nuovoImmobile.setDescrizione(immobileDto.getDescrizione());
        nuovoImmobile.setCategoria(immobileDto.getCategoria());
        nuovoImmobile.setPrezzo(immobileDto.getPrezzo());
        nuovoImmobile.setMq(immobileDto.getMq());
        nuovoImmobile.setCamere(immobileDto.getCamere());
        nuovoImmobile.setBagni(immobileDto.getBagni());
        nuovoImmobile.setAnno(immobileDto.getAnno());
        nuovoImmobile.setEtichetta(immobileDto.getEtichetta());
        nuovoImmobile.setProvincia(immobileDto.getProvincia());
        nuovoImmobile.setLatitudine(immobileDto.getLatitudine());
        nuovoImmobile.setLongitudine(immobileDto.getLongitudine());
        nuovoImmobile.setFotoPaths(new ArrayList<>());

        this.immobileService.createImmobile(nuovoImmobile,immobileDto.getFoto(),immobileDto.getUser());
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
