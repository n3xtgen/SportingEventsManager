package cz.muni.fi.PA165.dto;

import java.util.Date;

/**
 * @author n3xtgen
 */
public class EntryDTO {

    private Long idSportResult;
    private EntryState entryState;
    public enum EntryState { REGISTERED, STARTED, FINISHED, DISQUALIFIED}
    private int position;
    private Date time;

    /*************************
     *** GETTERS & SETTERS ***
     *************************/

    public Long getIdSportResult() {
        return idSportResult;
    }

    public void setIdSportResult(Long idSportResult) {
        this.idSportResult = idSportResult;
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
        if ( !(obj instanceof EntryDTO) ) return false;
        if (this == obj) return true;

        final EntryDTO entry = (EntryDTO) obj;

        if ( !getIdSportResult().equals(entry.getIdSportResult()) ) return false;
        if ( !getEntryState().equals(entry.getEntryState()) ) return false;
        if ( getPosition() != entry.getPosition() ) return false;
        if ( !getTime().equals(entry.getTime()) ) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int hash;
        hash = 13 * getIdSportResult().hashCode();
        hash = hash + 17 * getEntryState().hashCode();
        hash = hash + 29 * getPosition();
        hash = hash + 37 * getTime().hashCode();
        return hash;
    }

    @Override
    public String toString()
    {
        return "EventDTO{"
                + "id= " + idSportResult
                + ", entryState= " + entryState.toString()
                + ", position= " + position
                + ", time= " + time.toString()
                + '}';
    }
}
