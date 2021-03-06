package cz.muni.fi.PA165.dto.facade;

import cz.muni.fi.PA165.dto.CreateEventDTO;
import cz.muni.fi.PA165.dto.EventDTO;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Jamik on 25.11.2015.
 */
public interface EventFacade {

    /**
     * Find event by ID
     * @param id
     * @return event with spcified ID
     */
    EventDTO findEventById(Long id);

    /**
     * Find evnet by name
     * @param name
     * @return event with specified name
     */
    EventDTO findEventByName(String name);

    /**
     * Get all events
     * @return collection of all events
     */
    Collection<EventDTO> getAllEvents();

    /**
     * Find events that take place between startDate & endDate
     * @param startDate
     * @param endDate
     * @return collection of events that take place between startDate & endDate
     */
    Collection<EventDTO> findEventsInDateRange(Date startDate, Date endDate);

    /**
     * Add new event
     * @param evt
     */
    void addEvent(CreateEventDTO evt);

    /**
     * Remove an event
     * @param eventId
     * @return success/failure
     */
    boolean deleteEvent(Long eventId);

    /**
     * Update existing event
     * @param evt
     */
    void updateEvent(EventDTO evt);

    /**
     * Add sport to the event
     * @param eventId
     * @param sportId
     */
    void addSport(Long eventId, Long sportId);

    /**
     * Remove sport from an event
     * @param eventId
     * @param sportId
     * @return success/failure
     */
    boolean removeSport(Long eventId, Long sportId);
}
