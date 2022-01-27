package com.mtmd.eiscrememanager.api;

import com.mtmd.eiscrememanager.control.EiscremeService;
import com.mtmd.eiscrememanager.mapper.EiscremesorteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Endpunkt für die Verwaltung von Eiscremesorten
 */

@RestController
public class EiscremeResource extends EiscremeManagerResource{

    private final EiscremeService eiscremeService;
    private final EiscremesorteMapper mapper;

    @Autowired
    public EiscremeResource(EiscremeService eiscremeService, EiscremesorteMapper mapper) {
        this.eiscremeService = eiscremeService;
        this.mapper = mapper;
    }

    /**
     * Methode für das Laden aller Eiscremesorten
     *
     * @return Eine Liste mit allen gespeicherten Eiscremesorten
     *
     */

    @GetMapping(value = "/eiscremesorten")
    ResponseEntity<List<EiscremesorteDto>> ladeAlleEiscremesorten(){
        return ResponseEntity.ok(mapper.mapToDtos(eiscremeService.ladeAlleEiscremesorten()));
    }

    /**
     * Methode für die Anlage neuer Eiscremesorten
     *
     * @param eiscremesorteDto eine Eiscremesorte in gültigem Json-Format, Felder dürfen weder null-Werte, noch leere String enthalten,
     * siehe {@link EiscremesorteDto}
     *
     * @return Die neu erstellte Eiscremesorte
     */

    @PostMapping(value = "/eiscremesorten")
    ResponseEntity<EiscremesorteDto> erstelleEiscremesorte(@RequestBody @Valid EiscremesorteDto eiscremesorteDto){
        return ResponseEntity.ok(mapper.mapToDto(eiscremeService.erzeugeEiscreme(mapper.mapToEntity(eiscremesorteDto))));
    }


}
