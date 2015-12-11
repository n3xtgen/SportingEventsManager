package cz.muni.fi.PA165.dto;

import java.util.Date;
import java.util.Set;

/**
 * Created by Jamik on 25.11.2015.
 */
public class EventDTO {

    private Long        idEvent;
    private String      name;
    private String      description;
    private Date        startTime;
    private Date        endTime;
    private Set<SportDTO>  sports;

    /*********************
     * GETTERS & SETTERS *
     *********************/

    public Long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

    public String getName() {
        System.out.println("public String getName() " + name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<SportDTO> getSports() {
        System.out.println("public Set<SportDTO> getSports(): " + (sports == null ? 0 : sports.size()));
        return sports;
    }

    public void setSports(Set<SportDTO> sports) {
        this.sports = sports;
    }

    /**********
    * METHODS *
    ***********/

    /**
     * Computes hash code
     * @return hash code computed with name attribute
     */
    @Override
    public int hashCode(){
        // NOTE: 79, 29 are random prime numbers
        int hash = 79;
        hash = 29 * hash + (name == null ? 0 : name.hashCode());
        hash = 29 * hash + (startTime == null ? 0 : startTime.hashCode());
        hash = 29 * hash + (endTime == null ? 0 : endTime.hashCode());
        return hash;
    }

    /**
     * Compares objects
     * @param toCompare
     * @return
     */
    @Override
    public boolean equals(Object toCompare) {
        if(toCompare == null || !(toCompare instanceof EventDTO))
            return false;

        if(this == toCompare)
            return true;
        // check Events name
        EventDTO other = (EventDTO)toCompare;
        // make sure null == null returns true
        return (name == null ? other.getName() == null : name.equals(other.getName()) &&
                startTime == null ? other.getStartTime() == null : startTime.equals(other.getStartTime()) &&
                endTime == null ? other.getEndTime() == null : endTime.equals(other.getEndTime()));
    }

    /**
     * Put object attributes into string
     * @return string with all attributes
     */
    @Override
    public String toString()
    {
        return "EventDTO{"
                + "id= " + idEvent
                + ", name= " + name
                + ", description= " + description
                + ", start= " + startTime.toString()
                + ", end= " + endTime.toString()
                + '}';
    }
}
