package cz.muni.fi.PA165.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

/**
 * Created by Vladimir on 24.11.2015.
 */
public class SportDTO {

    private Long idSport;
    private String name;

    private Date startTime;
    private Date endTime;

    private EventDTO event;

    private List<EntryDTO> entries = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getIdSport() {
        return idSport;
    }

    public void setIdSport(Long idSport) {
        this.idSport = idSport;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public List<EntryDTO> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryDTO> entries) {
        this.entries = entries;
    }

    /**
     * Lets check whether or not is the sportsman already registered to the sport
     * @param id
     * @return
     */
    public boolean isSportsmanRegistred(Long id){
        for(Iterator<EntryDTO> itr = entries.iterator(); itr.hasNext();)
            if (itr.next().getUsr().getId() == id) // the sportsman is already registred in this sport
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
        SportDTO other = (SportDTO) obj;
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

    /**
     * @NOTE: if you ever happen to change this method, please make sure to change dependent SportDTOPropertyEditor
     */
    @Override
    public String toString() {
        return "SportDTO{"
                + "id=" + idSport
                + "; name=" + name
                + "; startTime=" + startTime
                + "; endTime=" + endTime
                + '}';
    }

}
