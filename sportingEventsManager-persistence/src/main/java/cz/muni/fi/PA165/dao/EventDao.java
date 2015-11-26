package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Event;

import java.util.List;

/**
 * @author Jamik (Lukas Gryc)
 */
public interface EventDao {

    /**
     * create new event
     * @param evt
     */
    public void create(Event evt);

    /**
     * delete given event
     * @param evt
     */
    public void delete(Event evt);

    /**
     * update event
     * @param evt
     * @return event
     */
    public Event update(Event evt);

    /**
     * find event by its ID
     * @param id
     * @return event with given ID
     */
    public Event findById(Long id);

    /**
     * find event by its name
     * @param name
     * @return event with specified name
     */
    public Event findByName(String name);

    /**
     * find all events
     * @return all events
     */
    public List<Event> findAll();
}
