package cz.muni.fi.PA165.dto;


import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Jamik on 25.11.2015.
 */
public class EventDTO {

    private Long    idEvent;
    private String  name;
    private String  description;
    private Date    startTime;
    private Date    endTime;

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
        return (79 * 29 + (name == null ? 0 : name.hashCode()));
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
        if(getName() == null){
            if(other.getName() != null)
                return false;
        }
        else if(!getName().equals(other.getName())) // check the name
            return false;

        return true;
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