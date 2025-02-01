
package it.unical.demacs.informatica.easyhomebackend.controller;



import it.unical.demacs.informatica.easyhomebackend.model.UserRole;
import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.service.IUserService;
import it.unical.demacs.informatica.easyhomebackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/open")
public class UtenteController {

    private final IUserService userService;



    @DeleteMapping("/users/{username}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable String username) {
        boolean isDeleted = userService.deleteUser(username);
        Map<String, String> response = new HashMap<>();
        if (isDeleted) {
            response.put("message", "Utente eliminato con successo");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Utente non trovato");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    public UtenteController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/createUser" , method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Utente utente) {
        utente.setRole(UserRole.ROLE_USER);
        this.userService.createUser(utente);

        return  ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/users" , method = RequestMethod.GET)
    public List<String> getAllUsernames() {
        return userService.getAllUsernames();
    }



}