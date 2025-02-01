
package it.unical.demacs.informatica.easyhomebackend.controller;



import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.model.UserRole;
import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.persistence.dto.UserRoleDto;
import it.unical.demacs.informatica.easyhomebackend.service.IUserService;
import it.unical.demacs.informatica.easyhomebackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "open/users", method = RequestMethod.GET)
    public List<UserRoleDto> getAllUsers() {
        List<UserRoleDto> users = userService.getAllUsernamesAndRoles();  // Usa il nuovo metodo
        return users;  // Restituisci la lista con username e ruolo
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