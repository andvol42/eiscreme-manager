package com.mtmd.eiscrememanager.domain;

import com.mtmd.eiscrememanager.infrastructure.JpaEiscremesorteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Domain-Service der prüfen soll, ob eine Eiscremesorte bereits vorhanden ist
 * Eine Eiscremesorte gilt als bereits vorhanden, wenn entweder eine Eiscremesorte in der Datenbank mit dem selben Namen
 * ODER mit den selben Zutaten gefunden wird
 */
@Component
public class EiscremesorteValidierung {

    private final JpaEiscremesorteRepository repository;

    @Autowired
    public EiscremesorteValidierung(JpaEiscremesorteRepository repository) {
        this.repository = repository;
    }

    // Name und Zutaten duerfen nicht uebereinstimmen
    public Boolean eiscremeBereitsVorhanden(Eiscremesorte eiscremesorte) {
        List<Eiscremesorte> eiscremesorteDaten = repository.ladeAlle();
        List<Eiscremesorte> uebereinstimmungenZutaten = eiscremesorteDaten.stream().filter(e -> e.getZutaten().equals(eiscremesorte.getZutaten()))
                .collect(Collectors.toList());

        boolean namenGleich = eiscremesorteDaten
                .stream().anyMatch(e -> e.getName().equalsIgnoreCase(eiscremesorte.getName()));
        return !uebereinstimmungenZutaten.isEmpty() || namenGleich;
    }
}
