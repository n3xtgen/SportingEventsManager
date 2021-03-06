package cz.muni.fi.PA165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;

/**
 * Trida reprezentujici registraci sportovce v jednom konkretnim zavode (sportu), poradanem v ramci sportovni udalosti.
 * Obsahuje take sportovcovy vysledky.
 *
 * @author n3xtgen
 */
@Entity
public class Entry {

    /**
     * Id prihlasky sportovce k zavodu.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntry;

    /**
     * Sport (zavod), ke kteremu se vysledek vaze.
     */
    @NotNull
    @ManyToOne
    private Sport sport;

    /**
     * Sportovec, ktereho se vysledek tyka.
     */
    @NotNull
    @ManyToOne
    private Usr usr;

    /**
     * Stav ve kterem se sportovec v ramci zavodu nachazi.
     */
    @Enumerated
    private EntryState entryState;
    public enum EntryState {DISQUALIFIED, REGISTERED, FINISHED} // Hodnoty sou serazeny v poradi vyhodnosti od nejmensi po nejvetsi, pro serazeni vysledku..

    /**
     * Poradi (vysledne misto) sportovce.
     */
    private int position;

    /**
     * Vysledny cas sportovce.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    /********************
     *** CONSTRUCTORS ***
     ********************/

    public Entry(){
    }

    /*************************
     *** GETTERS & SETTERS ***
     *************************/

    public Long getIdEntry() {
        return idEntry;
    }

    public void setIdEntry(Long idEntry) {
        this.idEntry = idEntry;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Usr getUsr() {
        return usr;
    }

    public void setUsr(Usr usr) {
        this.usr = usr;
    }

    public EntryState getEntryState() {
        return entryState;
    }

    public void setEntryState(EntryState entryState) {
        this.entryState = entryState;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    /***************************
     *** METHODS & FUNCTIONS ***
     ***************************/

    @Override
    public boolean equals(Object obj) {
        if ( !(obj instanceof Entry) ) return false;
        if (this == obj) return true;

        final Entry entry = (Entry) obj;
        if ( !getSport().equals(entry.getSport()) ) return false;
        if ( !getUsr().equals(entry.getUsr()) ) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int hash;
        hash = 13 * getSport().hashCode();
        hash = hash + 29 * getUsr().hashCode();
        return hash;
    }
}
