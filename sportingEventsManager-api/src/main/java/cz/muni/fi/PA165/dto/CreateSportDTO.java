package cz.muni.fi.PA165.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Vladimir on 24.11.2015.
 */
public class CreateSportDTO {

    private Long idSport;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    private Date startTime;

    private Date endTime;

    private EventDTO event;

    public Long getIdSport() {
        return idSport;
    }

    public void setIdSport(Long idSport) {
        this.idSport = idSport;
    }

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

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
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
        if (!getName().equals(((CreateSportDTO)obj).getName())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CreateSportDTO{"
                + "name=" + name
                + ", startTime=" + startTime
                + ", endTime=" + endTime
                + ", event=" + event.toString()
                + '}';
    }

}
