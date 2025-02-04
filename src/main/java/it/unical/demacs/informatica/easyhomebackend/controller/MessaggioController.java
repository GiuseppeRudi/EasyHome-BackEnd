package it.unical.demacs.informatica.easyhomebackend.controller;

import it.unical.demacs.informatica.easyhomebackend.model.Messaggio;
import it.unical.demacs.informatica.easyhomebackend.persistence.dto.MessaggioDto;
import it.unical.demacs.informatica.easyhomebackend.service.IMessaggioService;
import it.unical.demacs.informatica.easyhomebackend.service.MessaggioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessaggioController {
    private final IMessaggioService messaggioService= new MessaggioService();


    public MessaggioController() { }

    @PostMapping("/auth/messaggi/createMessaggio")
    public ResponseEntity<Void> createMessaggio(
            @ModelAttribute MessaggioDto messaggioDto) {

        System.out.println(messaggioDto.getAcquirente());
        System.out.println("porco");
        Messaggio messaggio=new Messaggio(messaggioDto.getOggetto(), messaggioDto.getDescrizione());
        this.messaggioService.createMessaggio(messaggio,messaggioDto.getAcquirente(),messaggioDto.getIdImmobile());
        return ResponseEntity.ok().build();
    }

}
