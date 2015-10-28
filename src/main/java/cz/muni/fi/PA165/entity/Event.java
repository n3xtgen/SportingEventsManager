package cz.muni.fi.PA165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Jamik on 28.10.2015.
 */
@Entity
public class Event{

    // event id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of the event
    @Column(nullable=false, unique=true)
    private String name;

    // Description of the event
    private String description;

    // time&date of events start
    @NotNull
    private Date start;

    // time&date of the events end
    @NotNull
    private Date end;


    public Event(){
    }

    public Event(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

}
