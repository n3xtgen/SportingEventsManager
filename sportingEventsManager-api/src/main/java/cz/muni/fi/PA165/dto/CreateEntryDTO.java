package cz.muni.fi.PA165.dto;

import javax.validation.constraints.NotNull;

/**
 * @author n3xtgen
 */
public class CreateEntryDTO {

    private Long entryId;

    @NotNull
    private Long sportId;

    @NotNull
    private Long sportsmanId;


    /*************************
     *** GETTERS & SETTERS ***
     *************************/

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }

    public Long getSportsmanId() {
        return sportsmanId;
    }

    public void setSportsmanId(Long sportsmanId) {
        this.sportsmanId = sportsmanId;
    }

    /***************************
     *** METHODS & FUNCTIONS ***
     ***************************/

    @Override
    public boolean equals(Object obj) {
        if ( !(obj instanceof EntryDTO) ) return false;
        if (this == obj) return true;

        final CreateEntryDTO entry = (CreateEntryDTO) obj;
        return (getSportId() == null ? entry.getSportId() == null : getSportId().equals(entry.getSportId()) &&
                getSportsmanId() == null ? entry.getSportsmanId() == null : getSportsmanId().equals(entry.getSportsmanId()));
    }

    @Override
    public int hashCode(){
        int hash = 13 * getSportId().hashCode();
        hash += 17 * getSportsmanId().hashCode();
        return hash;
    }


    @Override
    public String toString()
    {
        return "EventDTO{"
                + "sportId= " + sportId
                + "; sportsmanId= " + sportsmanId
                + '}';
    }
}
