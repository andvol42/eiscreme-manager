package com.mtmd.eiscrememanager.domain;

import com.mtmd.eiscrememanager.domain.Eiscremesorte;
import com.mtmd.eiscrememanager.domain.EiscremesorteValidierung;
import com.mtmd.eiscrememanager.infrastructure.JpaEiscremesorteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class EiscremeValidierungSpecTest {

    @Mock
    private JpaEiscremesorteRepository mockedRepository;
    private EiscremesorteValidierung unitUnderTest;
    private Eiscremesorte sahneeis;
    private Eiscremesorte schokoeis;

    @BeforeEach
    void setUp() {
        unitUnderTest = new EiscremesorteValidierung(mockedRepository);
        sahneeis = new Eiscremesorte(1l, "Sahneeis", Eiscremesorte.Kategorie.SAHNEEIS, Set.of("Sahne", "Milch", "Zucker"), 134, BigDecimal.valueOf(2.6));
        schokoeis = new Eiscremesorte(2l, "Schokoeis", Eiscremesorte.Kategorie.SAHNEEIS, Set.of("Sahne", "Milch", "Kakao"), 256, BigDecimal.valueOf(2.6));
    }

    @Test
    @DisplayName("Liefere true, wenn eine Eiscremesorte mit dem selben Namen bereits in der Datenbank gespeichert ist")
    void lieferTrueWennNamenUebereinstimmen() {
        // given
        Mockito.when(mockedRepository.ladeAlle()).thenReturn(List.of(sahneeis, schokoeis));
        Eiscremesorte neueSorteNameLowercase = new Eiscremesorte("sahneeis", Eiscremesorte.Kategorie.SAHNEEIS, Set.of("Sahne", "Milch"), 134, BigDecimal.valueOf(2.6));

        // when
        Boolean sorteBereitsVorhanden = unitUnderTest.eiscremeBereitsVorhanden(neueSorteNameLowercase);

        // then
        assertThat(sorteBereitsVorhanden).isTrue();
    }

    @Test
    @DisplayName("Liefere true, wenn eine Eiscremesorte mit den selben Zutaten bereits in der Datenbank gespeichert ist")
    void lieferTrueWennZutatenUebereinstimmen() {
        // given
        Mockito.when(mockedRepository.ladeAlle()).thenReturn(List.of(sahneeis, schokoeis));
        Eiscremesorte neueSorte = new Eiscremesorte("Karamell", Eiscremesorte.Kategorie.SAHNEEIS, Set.of("Sahne", "Milch", "Zucker"), 134, BigDecimal.valueOf(2.6));

        // when
        Boolean sorteBereitsVorhanden = unitUnderTest.eiscremeBereitsVorhanden(neueSorte);

        // then
        assertThat(sorteBereitsVorhanden).isTrue();
    }
}
