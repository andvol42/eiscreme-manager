package com.mtmd.eiscrememanager.infrastructure;

import com.mtmd.eiscrememanager.domain.Eiscremesorte;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Repository, das von {@link com.mtmd.eiscrememanager.control.EiscremeService}
 * und {@link com.mtmd.eiscrememanager.domain.EiscremesorteValidierung} als Schnittstelle zur Datenbank genutzt wird
 *
 */

@Repository
public class JpaEiscremesorteRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Eiscremesorte> ladeAlle(){
        return entityManager.createQuery("SELECT a FROM Eiscremesorte a").getResultList();
    }

    @Transactional
    public Eiscremesorte persistiere(Eiscremesorte eiscremesorte){
        entityManager.persist(eiscremesorte);
        return eiscremesorte;
    }
}
