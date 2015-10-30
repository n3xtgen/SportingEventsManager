package cz.muni.fi.PA165.entity;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vladimir
 */
@Entity
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSport;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sportTypes")
    private Set<Event> eventsContainingSport = new HashSet<Event>();

    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name="sport_sportsman", joinColumns = {
            @JoinColumn(name="idSport", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="idSportsman",
                    nullable = false, updatable = false)})
    private Set<Sportsman> competitors = new HashSet<Sportsman>();

    public Sport(Long id){
        this.idSport = id;
    }

    public Sport(){}

    public Long getIdSport() {
        return idSport;
    }

    public void setIdSport(Long id) {
        this.idSport = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Event> getEventsContainingSport() {
        return eventsContainingSport;
    }

    public void setEventsContainingSport(Set<Event> eventsContainingSport) {
        this.eventsContainingSport = eventsContainingSport;
    }

    public Set<Sportsman> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(Set<Sportsman> competitors) {
        this.competitors = competitors;
    }

    public void addEventsContainingSport(Event event){
        this.eventsContainingSport.add(event);
    }

    public void removeEventContainingSport(Event event){
        this.eventsContainingSport.remove(event);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (! (obj instanceof Sport))
            return false;
        Sport other = (Sport) obj;
        if (name == null) {
            if (other.getName() != null)
                return false;
        } else if (!name.equals(other.getName()))
            return false;
        return true;
    }
}
