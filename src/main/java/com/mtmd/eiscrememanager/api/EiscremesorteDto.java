package com.mtmd.eiscrememanager.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

/**
 *
 * DTO für die Repräsentation einer Eiscremesorte im Json-Format
 * Alle Felder werden auf null-Werte und leere Strings validiert
 *
 */
public class EiscremesorteDto {
    private Long id;

    @NotBlank(message = "Name darf nicht leer sein")
    private String name;

    @NotBlank(message = "Kategorie darf nicht leer sein")
    private String kategorie;

    @NotEmpty(message = "Zutaten darf nicht leer sein")
    private Set<String> zutaten;

    @NotNull(message = "Kcal darf nicht leer sein")
    private Integer kcal;

    @NotNull(message = "Preis darf nicht leer sein")
    private BigDecimal preis;

    public EiscremesorteDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public Set<String> getZutaten() {
        return zutaten;
    }

    public void setZutaten(Set<String> zutaten) {
        this.zutaten = zutaten;
    }

    public Integer getKcal() {
        return kcal;
    }

    public void setKcal(Integer kcal) {
        this.kcal = kcal;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }

}
