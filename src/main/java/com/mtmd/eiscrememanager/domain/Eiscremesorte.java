package com.mtmd.eiscrememanager.domain;

/**
 * Die Entity-Repräsentation einer Eiscremesorte
 * Alle Felder werden mindestens auf Null-Werte validiert
 *
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "eiscremesorte")
public class Eiscremesorte {

    /**
     * Mögliche Kategorieausprägungen für den Client
     *
     */
    public enum Kategorie{
        SAHNEEIS("Sahne-Eis"),
        FRUCHTEIS("Frucht-Eis"),
        WASSEREIS("Wasser-Eis");

        private String bezeichnung;

        public String getBezeichnung(){
            return bezeichnung;
        }

        Kategorie(String bezeichnung){
            this.bezeichnung = bezeichnung;
        }

        public static Kategorie fromString(String value){
            for(Kategorie kategorie : Kategorie.values()){
                if(value.equalsIgnoreCase(kategorie.bezeichnung)){
                    return kategorie;
                }
            }
            return null;
        }
    }


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eiscremesortedb_seq")
    @SequenceGenerator(name = "eiscremesortedb_seq", allocationSize = 1)
    private Long id;

    @NotNull(message = "Feld name muss in der DB vorhanden sein")
    private String name;

    @NotNull(message = "Feld kategorie muss in der DB vorhanden sein")
    @Enumerated(EnumType.STRING)
    private Kategorie kategorie;

    @NotNull(message = "Feld zutaten muss in der DB vorhanden sein")
    @ElementCollection
    private Set<String> zutaten = new HashSet();

    @NotNull(message = "Feld kcal muss in der DB vorhanden sein")
    private Integer kcal;

    @NotNull(message = "Feld preis muss in der DB vorhanden sein")
    private BigDecimal preis;

    public Eiscremesorte() {
    }

    public Eiscremesorte(String name, Kategorie kategorie, Set<String> zutaten, Integer kcal, BigDecimal preis) {
        this.name = name;
        this.kategorie = kategorie;

        if ( zutaten != null ) {
            this.zutaten.addAll( zutaten );
        }

        this.kcal = kcal;
        this.preis = preis;
    }

    public Eiscremesorte(Long id, String name, Kategorie kategorie, Set<String> zutaten, Integer kcal, BigDecimal preis) {
        this.id = id;
        this.name = name;
        this.kategorie = kategorie;

        if ( zutaten != null ) {
            this.zutaten.addAll( zutaten );
        }

        this.kcal = kcal;
        this.preis = preis;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public Set<String> getZutaten() {
        return zutaten;
    }

    public Integer getKcal() {
        return kcal;
    }

    public BigDecimal getPreis() {
        return preis;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eiscremesorte that = (Eiscremesorte) o;
        return id.equals(that.id) && name.equals(that.name) && kategorie == that.kategorie && zutaten.equals(that.zutaten) && kcal.equals(that.kcal) && preis.equals(that.preis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, kategorie, zutaten, kcal, preis);
    }

    @Override
    public String toString() {
        return "Eiscremesorte{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", kategorie=" + kategorie +
                ", zutaten=" + zutaten +
                ", kcal=" + kcal +
                ", preis=" + preis +
                '}';
    }
}
