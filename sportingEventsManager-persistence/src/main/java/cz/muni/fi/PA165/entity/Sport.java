package cz.muni.fi.PA165.entity;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Konkretni Sport (zavod), ktery se kona v ramci jednoho Eventu.
 *
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

    @NotNull
    @ManyToOne
    private Event event;

    /**
     * Seznam prihlasek jednotlivych sportovcu k zavodu a jejich vysledku.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sport", orphanRemoval = true)
    private Set<Entry> entries = new HashSet<Entry>();

    /********************
     *** CONSTRUCTORS ***
     ********************/

    public Sport(){
    }

    /*************************
     *** GETTERS & SETTERS ***
     *************************/

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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Set<Entry> getEntries() { return Collections.unmodifiableSet(entries); }

    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    public void removeEntry(Entry entry) {
        entries.remove(entry);
    }

    /***************************
     *** METHODS & FUNCTIONS ***
     ***************************/

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
