package cz.muni.fi.PA165.entity;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vladimir on 25.10.2015.
 */
@Entity
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany
    private Set<Event> eventsContainingSport = new HashSet<Event>();

    public Sport(Long id){
        this.id = id;
    }

    public Sport(){}

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

    public Set<Event> getEventsContainingSport() {
        return eventsContainingSport;
    }

    public void setEventsContainingSport(Set<Event> eventsContainingSport) {
        this.eventsContainingSport = eventsContainingSport;
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
