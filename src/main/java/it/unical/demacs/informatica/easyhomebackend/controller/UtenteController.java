
package it.unical.demacs.informatica.easyhomebackend.controller;



import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.model.UserRole;
import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UtenteController {

    private final IUserService userService;

    public UtenteController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/open/createUser" , method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Utente utente) {
        utente.setRole(UserRole.ROLE_USER);
        this.userService.createUser(utente);

        return  ResponseEntity.ok().build();
    }

    @GetMapping("/auth/{username}/immobili")
    public ResponseEntity<List<Immobile>> getImmobiliUtente(@PathVariable String username) {
        Optional<Utente> utenteOptional = userService.getUser(username);

        // Verifica se l'utente è presente nel Optional
        if (utenteOptional.isPresent()) {
            Utente utente = utenteOptional.get();  // Estrai l'utente dall'Optional
            return ResponseEntity.ok(utente.getImmobili());  // Ora puoi chiamare getImmobili
        } else {
            return ResponseEntity.notFound().build();  // Rispondi con un 404 se l'utente non è trovato
        }
    }

}