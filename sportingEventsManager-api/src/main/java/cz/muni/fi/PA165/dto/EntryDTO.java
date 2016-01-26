package cz.muni.fi.PA165.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author n3xtgen
 */
public class EntryDTO {

    private Long idEntry;
    private EntryState entryState;
    public enum EntryState {DISQUALIFIED, REGISTERED, FINISHED} // Hodnoty sou serazeny v poradi vyhodnosti od nejmensi po nejvetsi, pro serazeni vysledku..
    private int position;
    @DateTimeFormat(pattern="HH:mm:ss.SSS")
    private Date time;

    private SportDTO sport;
    private UserDTO usr;

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

    public UserDTO getUsr() {
        return usr;
    }

    public void setUsr(UserDTO usr) {
        this.usr = usr;
    }

    

    /***************************
     *** METHODS & FUNCTIONS ***
     ***************************/

    @Override
    public boolean equals(Object obj) {
        if ( !(obj instanceof EntryDTO) ) return false;
        if (this == obj) return true;

        final EntryDTO entry = (EntryDTO) obj;
        return (getSport() == null ? entry.getSport() == null : getSport().getIdSport().equals(entry.getSport().getIdSport()) &&
                getUsr() == null ? entry.getUsr() == null : getUsr().getId().equals(entry.getUsr().getId()));
    }

    @Override
    public int hashCode(){
        int hash = 13 * (getSport() == null ? 0 : getSport().hashCode());
        hash += 17 * (getUsr() == null ? 0 : getUsr().hashCode());
        return hash;
    }

    @Override
    public String toString()
    {
        return "EntryDTO{"
                + "id= " + idEntry
                + "; entryState= " + (entryState != null ? entryState.toString() : "null")
                + "; position= " + position
                + "; time= " + (time != null ? time.toString() : "null")
                + '}';
    }
}
