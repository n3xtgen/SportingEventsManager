package cz.muni.fi.PA165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jamik (Lukas Gryc)
 */
@Entity
public class Event{

    // event id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvent;

    // Name of the event
    @Column(nullable=false, unique=true)
    private String name;

    // Description of the event
    private String description;

    // time&date of events start
    @NotNull
    private Date startTime;

    // time&date of the events end
    @NotNull
    private Date endTime;

    // sportsmans registered to this event
    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name="event_sportsman", joinColumns = {
            @JoinColumn(name="idEvent", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="idSportsman",
            nullable = false, updatable = false)})
    private Set<Sportsman> registeredSportsmans = new HashSet<Sportsman>();

    // sports that are available in the event
    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name="event_sport", joinColumns = {
            @JoinColumn(name="idEvent", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="idSport",
                    nullable = false, updatable = false)})
    private Set<Sport> sportTypes = new HashSet<Sport>();

    /**
     * non-args constructor
     */
    public Event(){
    }

    /**
     * constructor
     * @param id
     */
    public Event(Long id){
        this.idEvent = id;
    }

    /******************************
     * BUNCH OF GETTERS&SETTERS ***
     ******************************/
    public Long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Long id) {
        this.idEvent = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date start) {
        this.startTime = start;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date end) {
        this.endTime = end;
    }

    public Set<Sportsman> getRegisteredSportsmans() {
        return registeredSportsmans;
    }

    public void setRegisteredSportsmans(Set<Sportsman> registeredSportsmans) {
        this.registeredSportsmans = registeredSportsmans;
    }

    public Set<Sport> getSportTypes() {
        return sportTypes;
    }

    public void setSportTypes(Set<Sport> sportTypes) {
        this.sportTypes = sportTypes;
    }

    /******************************
     *END OF GETTERS&SETTERS ******
     ******************************/

    /**
     * add sportsman to the event
     * @param sportsman
     */
    public void addSportsman(Sportsman sportsman){
        registeredSportsmans.add(sportsman);
    }

    /**
     * remove sportsman from the event
     * @param sportsman
     */
    public void removeSportsman(Sportsman sportsman){
        registeredSportsmans.remove(sportsman);
    }

    @Override
    public boolean equals(Object toCompare) {
        if(toCompare == null || !(toCompare instanceof Event))
            return false;

        if(this == toCompare)
            return true;
        // check Events name
        if(!name.equals(((Event) toCompare).getName()))
            return false;

        return true;
    }

    @Override
    public int hashCode(){
        // NOTE: 79, 29 are random prime numbers
        return (79 * 29 + (name == null ? 0 : name.hashCode()));
    }

}
