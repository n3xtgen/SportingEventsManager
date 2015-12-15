package cz.muni.fi.PA165.dto;

import java.util.Date;
import java.util.Iterator;

/**
 * @author n3xtgen
 */
public class EntryDTO {

    private Long idEntry;
    private EntryState entryState;
    public enum EntryState { REGISTERED, STARTED, FINISHED, DISQUALIFIED}
    private int position;
    private Date time;

    private SportDTO sport;
    private SportsmanDTO sportsman;

    /*************************
     *** GETTERS & SETTERS ***
     *************************/

    public Long getIdEntry() {
        return idEntry;
    }

    public void setIdEntry(Long idEntry) {
        this.idEntry = idEntry;
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

    public SportDTO getSport() {
        return sport;
    }

    public void setSport(SportDTO sport) {
        this.sport = sport;
    }

    public SportsmanDTO getSportsman() {
        return sportsman;
    }

    public void setSportsman(SportsmanDTO sportsman) {
        this.sportsman = sportsman;
    }

    public boolean haveFinished(){
        return time != null;
    }


    /***************************
     *** METHODS & FUNCTIONS ***
     ***************************/

    @Override
    public boolean equals(Object obj) {
        if ( !(obj instanceof EntryDTO) ) return false;
        if (this == obj) return true;

        final EntryDTO entry = (EntryDTO) obj;

        //if ( !getIdSportResult().equals(entry.getIdSportResult()) ) return false;
        //if ( !getEntryState().equals(entry.getEntryState()) ) return false;
        if ( getPosition() != entry.getPosition() ) return false;
        if ( !getTime().equals(entry.getTime()) ) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int hash;
//        hash = 13 * getIdSportResult().hashCode();
//        hash = hash + 17 * getEntryState().hashCode();
//        hash = hash + 29 * getPosition();
//        hash = hash + 37 * getTime().hashCode();
        return 57;
    }

    @Override
    public String toString()
    {
        return "EntryDTO{"
                + "id= " + idEntry
                + ", entryState= " + (entryState != null ? entryState.toString() : "null")
                + ", position= " + position
                + ", time= " + (time != null ? time.toString() : "null")
                + '}';
    }
}
