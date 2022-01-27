package com.mtmd.eiscrememanager.api;

/**
 * DTO, welches die möglichen Kategorien repräsentiert
 *
 */
public class KategorieDto {
    private String label;
    private String wert;

    public KategorieDto(String label, String wert) {
        this.label = label;
        this.wert = wert;
    }

    public String getLabel() {
        return label;
    }

    public String getWert() {
        return wert;
    }
}
