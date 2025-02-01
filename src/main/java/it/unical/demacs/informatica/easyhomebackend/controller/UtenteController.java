
package it.unical.demacs.informatica.easyhomebackend.controller;



import it.unical.demacs.informatica.easyhomebackend.model.UserRole;
import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.persistence.dto.UserRoleDto;
import it.unical.demacs.informatica.easyhomebackend.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/open")
public class UtenteController {

    private final IUserService userService;

    public UtenteController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/createUser" , method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Utente utente) {
        utente.setRole(UserRole.ROLE_USER);
        this.userService.createUser(utente);

        return  ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserRoleDto> getAllUsers() {
        List<UserRoleDto> users = userService.getAllUsernamesAndRoles();  // Usa il nuovo metodo
        return users;  // Restituisci la lista con username e ruolo
    }


}