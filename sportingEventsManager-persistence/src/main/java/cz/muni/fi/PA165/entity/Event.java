package cz.muni.fi.PA165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
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

    // sports that are available in the event
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private Set<Sport> sports = new HashSet<Sport>();

    /********************
     *** CONSTRUCTORS ***
     ********************/

    public Event(){
    }

    /*************************
     *** GETTERS & SETTERS ***
     *************************/

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

    public Set<Sport> getSports() { return Collections.unmodifiableSet(sports); }

    public void addSport(Sport sport) {
        sports.add(sport);
    }

    public void removeSport(Sport sport) {
        sports.remove(sport);
    }

    /***************************
     *** METHODS & FUNCTIONS ***
     ***************************/

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
