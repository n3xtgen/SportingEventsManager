package cz.muni.fi.PA165.entity;

import org.hibernate.mapping.Collection;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author N3xtGeN
 */
@Entity
public class Promoter {

    /**
     * Databazove Id promotera.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idPromoter;

    /**
     * Jmeno promotera.
     */
    @NotNull
    private String name;

    /**
     * Prijmeni promotera.
     */
    @NotNull
    private String surname;

    /**
     * Cislo obcanskeho prukazu promotera.
     */
    @NotNull
    @Column(nullable=false,unique=true)
    private String citizenIdNumber;

    /**
     * Seznam sportovnich udalosti, ktere promoterovy patri a ktere ovlada.
     */
    @OneToMany(mappedBy = "promoter", fetch = FetchType.LAZY)
    private Set<Event> ownedEvents = new HashSet<>();

    /********************
     *** CONSTRUCTORS ***
     ********************/

    public Promoter() {
    }

    /*************************
     *** GETTERS & SETTERS ***
     *************************/

    public Long getIdPromoter() { return idPromoter; }

    public void setIdPromoter(Long id) {  this.idPromoter = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCitizenIdNumber() {
        return citizenIdNumber;
    }

    public void setCitizenIdNumber(String citizenIdNumber) {
        this.citizenIdNumber = citizenIdNumber;
    }

    public Set<Event> getOwnedEvents() { return Collections.unmodifiableSet(ownedEvents); }

    public void setOwnedEvents(Set<Event> ownedEvents) { this.ownedEvents = ownedEvents; }

    /***************************
     *** METHODS & FUNCTIONS ***
     ***************************/

    /**
     * Prida pod spravu promotera novy event.
     *
     * @param event Event pro pridani
     */
    public void addOwnedEvent(Event event) {
        ownedEvents.add(event);
    }

    /**
     * Odebere event ze spravy promotera.
     *
     * @param event Event pro odebrani
     */
    public void removeOwnedEvent(Event event) {
        ownedEvents.remove(event);
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash = 23 * hash + Objects.hashCode(getCitizenIdNumber());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Promoter)) {
            return false;
        }
        Promoter promoter = (Promoter) obj;
        return citizenIdNumber.equals(promoter.getCitizenIdNumber());
    }
}
