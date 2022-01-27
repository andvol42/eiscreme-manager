package com.mtmd.eiscrememanager.api;

import com.mtmd.eiscrememanager.domain.Eiscremesorte;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Endpunkt für mögliche Kategorie-Ausprägungen im Client
 */

@RestController
public class ConfigResource extends EiscremeManagerResource{

    @GetMapping("/config/kategorien")
    ResponseEntity<List<KategorieDto>> ladeKategorien() {

        return ResponseEntity.ok(
                Arrays.stream(Eiscremesorte.Kategorie.values())
                        .map(k -> new KategorieDto(k.name(), k.getBezeichnung()))
                        .collect(Collectors.toList())
        );
    }
}
