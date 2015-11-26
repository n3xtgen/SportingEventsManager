package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.entity.Event;

import java.util.Collection;

/**
 * Created by Jamik on 25.11.2015.
 */
public interface EventService {

    Event findEventById(Long id);

    Event findEventByName(String name);

    Collection<Event> getAllEvents();

    void deleteEvent(Event evt);

    void updateEvent(Event evt);

    void createEvent(Event evt);
}
