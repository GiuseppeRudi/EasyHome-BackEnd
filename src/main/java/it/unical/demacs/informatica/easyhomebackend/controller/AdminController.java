
package it.unical.demacs.informatica.easyhomebackend.controller;



import it.unical.demacs.informatica.easyhomebackend.model.UserRole;
import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.persistence.dto.UserRoleDto;
import it.unical.demacs.informatica.easyhomebackend.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final IUserService userservice;

    public AdminController(IUserService userservice) {
        this.userservice = userservice;
    }


    public ResponseEntity<Void> createUser(@RequestBody Utente utente) {
        utente.setRole(UserRole.ROLE_USER);
        this.userservice.createUser(utente);

        return  ResponseEntity.ok().build();
    }

    @PostMapping("/change_role")
    public ResponseEntity<?> changeUserRole(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String newRole = request.get("newRole");

        if (username == null || newRole == null) {
            return ResponseEntity.badRequest().body("Errore: username e newRole sono obbligatori.");
        }

        userservice.changeUserRole(username, UserRole.valueOf(newRole));
        return ResponseEntity.ok("Ruolo aggiornato con successo!");
    }





}