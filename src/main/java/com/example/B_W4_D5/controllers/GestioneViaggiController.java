package com.example.B_W4_D5.controllers;

import com.example.B_W4_D5.entities.*;
import com.example.B_W4_D5.enumeration.StatoViaggio;
import com.example.B_W4_D5.services.GestioneViaggiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GestioneViaggiController {

    @Autowired
    private GestioneViaggiService service;

    @PostMapping("/dipendenti")
    public Dipendente creaDipendente(@RequestBody Dipendente d) {
        return service.salvaDipendente(d);
    }

    @GetMapping("/dipendenti")
    public List<Dipendente> getDipendenti() {
        return service.getTuttiIDipendenti();
    }

    @PostMapping("/viaggi")
    public Viaggio creaViaggio(@RequestBody Viaggio v) {
        return service.salvaViaggio(v);
    }

    @GetMapping("/viaggi")
    public List<Viaggio> getViaggi() {
        return service.getTuttiIViaggi();
    }

    @PostMapping("/prenotazioni")
    public Prenotazione prenota(@RequestParam Long dipendenteId,
                                @RequestParam Long viaggioId,
                                @RequestParam String dataRichiesta,
                                @RequestParam(required = false) String note) {
        return service.prenotaViaggio(dipendenteId, viaggioId, LocalDate.parse(dataRichiesta), note);
    }

    @PutMapping("/viaggi/{id}/stato")
    public Viaggio aggiornaStato(@PathVariable Long id, @RequestParam StatoViaggio stato) {
        return service.aggiornaStatoViaggio(id, stato);
    }
}
