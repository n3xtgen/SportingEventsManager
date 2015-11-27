package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.entity.Event;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Jamik on 25.11.2015.
 */
public interface EventService {

    /**
     * find event by its ID
     * @param id
     * @return event with specified ID
     */
    Event findEventById(Long id);

    /**
     * find event by its name
     * @param name
     * @return event with specified name
     */
    Event findEventByName(String name);

    /**
     * find events that take place between startDate & endDate
     * @param startDate
     * @param endDate
     * @return list of events that take place between startDate & endDate
     */
    Collection<Event> findEventsInDateRange(Date startDate, Date endDate);

    /**
     * get all events
     * @return collection of all events
     */
    Collection<Event> getAllEvents();

    /**
     * delete an event
     * @param evt
     */
    void deleteEvent(Event evt);

    /**
     * update an event
     * @param evt
     */
    void updateEvent(Event evt);

    /**
     * create an event
     * @param evt
     */
    void createEvent(Event evt);
}
