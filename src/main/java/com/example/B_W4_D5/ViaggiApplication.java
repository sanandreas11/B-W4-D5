package com.example.B_W4_D5;

import com.example.B_W4_D5.entities.*;
import com.example.B_W4_D5.enumeration.StatoViaggio;
import com.example.B_W4_D5.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class ViaggiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViaggiApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(
            DipendenteRepository dipRepo,
            ViaggioRepository viaggioRepo,
            PrenotazioneRepository prenotazioneRepo
    ) {
        return args -> {
            // Aggiunta dipendenti
            Dipendente mario = dipRepo.save(new Dipendente(null, "m.rossi", "Mario", "Rossi", "mario.rossi@email.com"));
            Dipendente lucia = dipRepo.save(new Dipendente(null, "l.bianchi", "Lucia", "Bianchi", "lucia.bianchi@email.com"));

            // Aggiunta viaggi
            Viaggio milano = viaggioRepo.save(new Viaggio(null, "Milano", LocalDate.of(2025, 6, 20), StatoViaggio.IN_PROGRAMMA));
            Viaggio roma = viaggioRepo.save(new Viaggio(null, "Roma", LocalDate.of(2025, 7, 10), StatoViaggio.IN_PROGRAMMA));

            // Aggiunta prenotazione
            Prenotazione prenotazione = new Prenotazione(null, LocalDate.of(2025, 6, 15), "Volo mattutino", mario, milano);
            prenotazioneRepo.save(prenotazione);
        };
    }
}
