package cz.muni.fi.PA165.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Jamik on 25.11.2015.
 */
public class CreateEventDTO {

    @NotNull
    @Size(min = 5, max = 50)
    private String  name;

    private String  description;

    // TODO: start time cant be >= end time, @Future is not sufficient enough
    // most likely we will need to extend validation class
    @NotNull
    @Future
    @AssertTrue public boolean isStartDateValid(){
        return endTime == null ? true : (startTime.getTime() < endTime.getTime() ? true : false);
    }
    private Date startTime;

    @NotNull
    @Future
    @AssertTrue public boolean isEndDateValid(){
        return startTime == null ? true : (startTime.getTime() < endTime.getTime() ? true : false);
    }
    private Date    endTime;


    /*********************
     * GETTERS & SETTERS *
     *********************/
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
                + "name= " + name
                + ", description= " + description
                + ", start= " + startTime.toString()
                + ", end= " + endTime.toString()
                + '}';
    }
}
