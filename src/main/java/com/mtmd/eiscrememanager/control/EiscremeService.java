package com.mtmd.eiscrememanager.control;

import com.mtmd.eiscrememanager.domain.Eiscremesorte;
import com.mtmd.eiscrememanager.domain.EiscremesorteValidierung;
import com.mtmd.eiscrememanager.exception.DuplicateResourceException;
import com.mtmd.eiscrememanager.infrastructure.JpaEiscremesorteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service für die Steuerung von eingehenden API-Calls
 *
 */

@Service
public class EiscremeService {

    private final JpaEiscremesorteRepository repository;
    private final EiscremesorteValidierung validierung;

    @Autowired
    public EiscremeService(JpaEiscremesorteRepository repository, EiscremesorteValidierung validierung) {
        this.repository = repository;
        this.validierung = validierung;
    }

    public List<Eiscremesorte> ladeAlleEiscremesorten() {
        return repository.ladeAlle();
    }

    /**
     *
     * Methode zum Anlegen einer neuen Eiscremesorte, nutzt {@link com.mtmd.eiscrememanager.domain.EiscremesorteValidierung}
     * um zu prüfen, ob eine Eiscremesorte bereits vorhanden ist, falls ja, wird eine Exception geworfen
     *
     * @param eiscremesorte Eine Eiscremesorte als Entity, die mithilfe eines Mappers aus einem DTO erzeugt wurde,
     * siehe {@link com.mtmd.eiscrememanager.mapper.EiscremesorteMapper}
     *
     * @return Die in der Datenbank persistierte Entity
     */
    @Transactional
    public Eiscremesorte erzeugeEiscreme(Eiscremesorte eiscremesorte){

        if (validierung.eiscremeBereitsVorhanden(eiscremesorte)) {
            throw new DuplicateResourceException("Eiscreme bereits vorhanden!");
        }
        repository.persistiere(eiscremesorte);
        return eiscremesorte;
    }
}
