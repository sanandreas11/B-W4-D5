package com.example.B_W4_D5.services;

import com.example.B_W4_D5.entities.*;
import com.example.B_W4_D5.enumeration.StatoViaggio;
import com.example.B_W4_D5.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GestioneViaggiService {

    @Autowired
    private DipendenteRepository dipRepo;

    @Autowired
    private ViaggioRepository viaggioRepo;

    @Autowired
    private PrenotazioneRepository prenotazioneRepo;

    public Dipendente salvaDipendente(Dipendente d) {
        return dipRepo.save(d);
    }

    public Viaggio salvaViaggio(Viaggio v) {
        return viaggioRepo.save(v);
    }

    public Prenotazione prenotaViaggio(Long dipId, Long viaggioId, LocalDate dataRichiesta, String note) {
        if (prenotazioneRepo.findByDipendenteIdAndDataRichiesta(dipId, dataRichiesta).isPresent()) {
            throw new RuntimeException("Prenotazione già esistente per questo dipendente in questa data");
        }

        Dipendente d = dipRepo.findById(dipId).orElseThrow(() -> new RuntimeException("Dipendente non trovato"));
        Viaggio v = viaggioRepo.findById(viaggioId).orElseThrow(() -> new RuntimeException("Viaggio non trovato"));

        Prenotazione p = new Prenotazione();
        p.setDipendente(d);
        p.setViaggio(v);
        p.setDataRichiesta(dataRichiesta);
        p.setNote(note);

        return prenotazioneRepo.save(p);
    }

    public Viaggio aggiornaStatoViaggio(Long idViaggio, StatoViaggio nuovoStato) {
        Viaggio v = viaggioRepo.findById(idViaggio).orElseThrow(() -> new RuntimeException("Viaggio non trovato"));
        v.setStato(nuovoStato);
        return viaggioRepo.save(v);
    }

    public List<Viaggio> getTuttiIViaggi() {
        return viaggioRepo.findAll();
    }

    public List<Dipendente> getTuttiIDipendenti() {
        return dipRepo.findAll();
    }
}
