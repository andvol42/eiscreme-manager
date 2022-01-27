package com.mtmd.eiscrememanager.mapper;

import com.mtmd.eiscrememanager.api.EiscremesorteDto;
import com.mtmd.eiscrememanager.domain.Eiscremesorte;
import com.mtmd.eiscrememanager.mapper.EiscremesorteMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class EiscremesortenMapperTest {

    private EiscremesorteMapper unitUnderTest = new EiscremesorteMapper();

    @Test
    @DisplayName("Mapper soll Dto korrekt auf Entität mappen")
    void mappeAufEntitaet(){
        // given
        EiscremesorteDto eiscremesorteDto = new EiscremesorteDto();
        eiscremesorteDto.setKategorie("Sahne-Eis");
        eiscremesorteDto.setKcal(290);
        eiscremesorteDto.setName("Sahneeis");
        eiscremesorteDto.setPreis(BigDecimal.valueOf(1.2));
        eiscremesorteDto.setZutaten(Set.of("Sahne", "Zucker", "Milch"));
        // when
        Eiscremesorte entity = unitUnderTest.mapToEntity(eiscremesorteDto);

        // then
        assertThat(entity.getKategorie()).isEqualTo(Eiscremesorte.Kategorie.SAHNEEIS);
        assertThat(entity.getName()).isEqualTo("Sahneeis");
        assertThat(entity.getKcal()).isEqualTo(290);
        assertThat(entity.getPreis()).isEqualTo(BigDecimal.valueOf(1.2));
        assertThat(entity.getZutaten()).isEqualTo(Set.of("Sahne", "Zucker", "Milch"));
    }


    @Test
    @DisplayName("Mapper soll Entität korrekt auf Dto mappen")
    void mappeAufDto(){
        // given

        Eiscremesorte eiscremesorte = new Eiscremesorte("Sahneeis", Eiscremesorte.Kategorie.SAHNEEIS, Set.of("Sahne", "Zucker", "Milch"), 290 ,BigDecimal.valueOf(1.2));

        // when
        EiscremesorteDto dto = unitUnderTest.mapToDto(eiscremesorte);

        // then
        assertThat(dto.getKategorie()).isEqualTo("Sahne-Eis");
        assertThat(dto.getName()).isEqualTo("Sahneeis");
        assertThat(dto.getKcal()).isEqualTo(290);
        assertThat(dto.getPreis()).isEqualTo(BigDecimal.valueOf(1.2));
        assertThat(dto.getZutaten()).isEqualTo(Set.of("Sahne", "Zucker", "Milch"));
    }
}
