
package it.unical.demacs.informatica.easyhomebackend.controller;



import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/open/v1")
public class UtenteController {

    private final IUserService userService;

    public UtenteController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/createUser" , method = RequestMethod.POST)

    public ResponseEntity<Void> createUser(@RequestBody Utente utente) {

        this.userService.createUser(utente.getUsername() , utente.getPassword(), utente.getRole());

        return  ResponseEntity.ok().build();
    }

}