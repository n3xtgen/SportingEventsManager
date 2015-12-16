package cz.muni.fi.PA165.dto;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Vladimir on 24.11.2015.
 */
public class SportDTO {

    private Long idSport;
    private String name;

    private Set<EntryDTO> entries = new HashSet<EntryDTO>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdSport() {
        return idSport;
    }

    public void setIdSport(Long idSport) {
        this.idSport = idSport;
    }

    public Set<EntryDTO> getEntries() {
        return entries;
    }

    public void setEntries(Set<EntryDTO> entries) {
        this.entries = entries;
    }

    /**
     * Lets check whether or not is the sportsman already registered to the sport
     * @param id
     * @return
     */
    public boolean isSportsmanRegistred(Long id){
        for(Iterator<EntryDTO> itr = entries.iterator(); itr.hasNext();)
            if(itr.next().getUser().getId() == id) // the sportsman is already registred in this sport
                return true;

        return false;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CreateSportDTO other = (CreateSportDTO) obj;
        if (getName() == null) {
            if (other.getName() != null) {
                return false;
            }
        } else if (!getName().equals(other.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "SportDTO{"
                + "id=" + idSport
                + "name=" + name
                + '}';
    }

}
