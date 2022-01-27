package com.mtmd.eiscrememanager.infrastructure;

import com.mtmd.eiscrememanager.domain.Eiscremesorte;
import com.mtmd.eiscrememanager.infrastructure.JpaEiscremesorteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionSystemException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class JpaEiscremesortenRepositoryIntegrationTest {

    @Autowired
    private JpaEiscremesorteRepository jpaEiscremesorteRepository;

    @Test
    void pruefeSetup(){
        assertThat(jpaEiscremesorteRepository).isNotNull();
    }

    @Test
    @DisplayName("Gültige Eiscremesorte sollte gespeichert werden")
    void speichereGueltigeEntitaet(){
        // given
        Eiscremesorte eiscremesorte = new Eiscremesorte("Sahne", Eiscremesorte.Kategorie.SAHNEEIS, Set.of("Milch", "Zucker", "Schlagsahne"), 230, BigDecimal.valueOf(3.2));
        // when
        Eiscremesorte entitaet =  jpaEiscremesorteRepository.persistiere(eiscremesorte);
        // then
        assertThat(entitaet.getId()).isNotNull();
        assertThat(entitaet.getId()).isEqualTo(3l);
    }

    @Test
    @Transactional
    @DisplayName("Prüfe, ob initiale Eiscremesorten in die DB geladen werden")
    void ladeInitialDaten(){

        assertThat(jpaEiscremesorteRepository.ladeAlle().stream().anyMatch(e ->
            e.getId()==1l &&
                    e.getName().equals("Bourbon Vanille") &&
                    e.getKategorie()==Eiscremesorte.Kategorie.SAHNEEIS &&
                    e.getZutaten().equals(Set.of("Milch", "Kokosfett", "Zucker", "Schlagsahne", "Bourbon-Vanilleextrakt", "Vanilleschoten")) &&
                    e.getKcal()==208 &&
                    e.getPreis().equals(BigDecimal.valueOf(3.65))
        )).isTrue();

        assertThat(jpaEiscremesorteRepository.ladeAlle().stream().anyMatch(e ->
                e.getId()==2l &&
                        e.getName().equals("Chocolate Chips") &&
                        e.getKategorie()==Eiscremesorte.Kategorie.SAHNEEIS &&
                        e.getZutaten().equals(Set.of("Milch", "Zucker", "Kakao", "Schlagsahne")) &&
                        e.getKcal()==219 &&
                        e.getPreis().equals(BigDecimal.valueOf(3.65))
        )).isTrue();
    }
}
