package it.unical.demacs.informatica.easyhomebackend.controller;

import it.unical.demacs.informatica.easyhomebackend.persistence.dao.UtenteDAO;
import it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc.UtenteDaoJDBC;
import it.unical.demacs.informatica.easyhomebackend.persistence.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.service.UtenteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    private final UtenteService utenteService = new UtenteService();




    @GetMapping
    public List<Utente> getAllUtenti() {
        return utenteService.findAll();
    }

    @PostMapping
    public String createUtente(@RequestBody Utente utente) {
        utenteService.save(utente);
        return "Utente salvato con successo!";
    }
}
