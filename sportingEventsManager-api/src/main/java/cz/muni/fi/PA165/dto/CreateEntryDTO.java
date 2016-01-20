package cz.muni.fi.PA165.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author n3xtgen
 */
public class CreateEntryDTO {

    private Long entryId;

    @NotNull
    private Long sportId;

    @DateTimeFormat(pattern = "HH:mm:SS")
    private Date time;

    private int position;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    /***************************
     *** METHODS & FUNCTIONS ***
     ***************************/

    @Override
    public boolean equals(Object obj) {
        if ( !(obj instanceof EntryDTO) ) return false;
        if (this == obj) return true;

        final CreateEntryDTO entry = (CreateEntryDTO) obj;

        if ( !getSportId().equals(entry.getSportId()) ) return false;
        if ( !getSportsmanId().equals(entry.getSportsmanId()) ) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int hash;
        hash = 13 * getSportId().hashCode();
        hash = hash + 17 * getSportsmanId().hashCode();
        return hash;
    }


    @Override
    public String toString()
    {
        return "EventDTO{"
                + "sportId= " + sportId
                + ", sportsmanId= " + sportsmanId
                + ", time= " + time
                + ", position= " + position
                + '}';
    }
}
