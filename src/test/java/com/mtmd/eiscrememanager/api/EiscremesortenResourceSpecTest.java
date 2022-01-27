package com.mtmd.eiscrememanager.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtmd.eiscrememanager.control.EiscremeService;
import com.mtmd.eiscrememanager.domain.Eiscremesorte;
import com.mtmd.eiscrememanager.domain.EiscremesorteValidierung;
import com.mtmd.eiscrememanager.infrastructure.JpaEiscremesorteRepository;
import com.mtmd.eiscrememanager.mapper.EiscremesorteMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.jupiter.api.*;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EiscremeResource.class)
public class EiscremesortenResourceSpecTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EiscremeService eiscremeService;

    @MockBean
    private EiscremesorteMapper eiscremesorteMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private Eiscremesorte sahneeis;
    private Eiscremesorte schokoeis;
    private EiscremesorteDto sahneeisDto;
    private EiscremesorteDto schokoeisDto;


    @BeforeEach
    void setUp(){
        sahneeis = new Eiscremesorte(1l, "Sahneeis", Eiscremesorte.Kategorie.SAHNEEIS, Set.of("Sahne", "Milch", "Zucker"), 134, BigDecimal.valueOf(2.6));
        schokoeis = new Eiscremesorte(2l, "Schokoeis", Eiscremesorte.Kategorie.SAHNEEIS, Set.of("Sahne", "Milch", "Kakao"), 256, BigDecimal.valueOf(2.6));

        sahneeisDto = new EiscremesorteDto();
        sahneeisDto.setName("Sahneeis");
        sahneeisDto.setKategorie( "Sahne-Eis");
        sahneeisDto.setZutaten(Set.of("Sahne", "Milch", "Zucker"));
        sahneeisDto.setKcal( 134);
        sahneeisDto.setPreis(BigDecimal.valueOf(2.6));

        schokoeisDto = new EiscremesorteDto();
        schokoeisDto.setName("Schokoeis");
        schokoeisDto.setKategorie( "Sahne-Eis");
        schokoeisDto.setZutaten(Set.of("Sahne", "Milch", "Kakao"));
        schokoeisDto.setKcal( 256);
        schokoeisDto.setPreis(BigDecimal.valueOf(2.6));



    }

    @Test
    void pruefeSetup(){
        assertThat(mockMvc).isNotNull();
        assertThat(eiscremeService).isNotNull();
        assertThat(eiscremesorteMapper).isNotNull();
    }

    @Test
    @DisplayName("Eiscremesorten können erfolgreich über einen GET Request vom Server angefragt werden")
    void ladeEiscremesorten() throws Exception {
        // given
        when(eiscremeService.ladeAlleEiscremesorten()).thenReturn(List.of(sahneeis, schokoeis));
        when(eiscremesorteMapper.mapToDtos(List.of(sahneeis, schokoeis))).thenReturn(List.of(sahneeisDto,schokoeisDto));

        // when
        // then
        mockMvc.perform(get("/eiscrememanager/eiscremesorten"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DisplayName("Bei einem POST Request mit einer ungültigen Eiscremesorte, soll ein 400 HTTP Status geworfen werden")
    void erzeugeUngueltigeSorte() throws Exception {

        // given
        EiscremesorteDto ungueltigesDto = new EiscremesorteDto();
        ungueltigesDto.setKategorie( "Sahne-Eis");
        ungueltigesDto.setZutaten(Set.of("Sahne", "Milch", "Kakao"));
        ungueltigesDto.setKcal( 256);
        ungueltigesDto.setPreis(BigDecimal.valueOf(2.6));

        // when
        // then
        mockMvc.perform(post("/eiscrememanager/eiscremesorten")
                .content(objectMapper.writeValueAsString(ungueltigesDto))
                .contentType("application/json"))
                .andExpect(status().isBadRequest());

    }
}
