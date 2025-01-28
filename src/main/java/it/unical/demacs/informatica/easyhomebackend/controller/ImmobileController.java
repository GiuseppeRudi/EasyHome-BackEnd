package it.unical.demacs.informatica.easyhomebackend.controller;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.service.IImmobileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/immobili")
public class ImmobileController {

    private final IImmobileService immobileService;

    public ImmobileController(IImmobileService immobileService) {
        this.immobileService = immobileService;
    }

    @RequestMapping(value = "/createImmobile", method = RequestMethod.POST)
    public ResponseEntity<Void> createImmobile(@RequestBody Immobile immobile) {
        this.immobileService.createImmobile(immobile);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<Immobile>> getAllImmobili() {
        List<Immobile> immobili = this.immobileService.getAllImmobili();
        return ResponseEntity.ok(immobili);
    }

}
