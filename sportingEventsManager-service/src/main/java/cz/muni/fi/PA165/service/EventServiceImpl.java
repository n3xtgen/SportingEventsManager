package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.Exceptions.DataAccessException;
import cz.muni.fi.PA165.dao.EventDao;
import cz.muni.fi.PA165.dao.SportDao;
import cz.muni.fi.PA165.entity.Event;
import cz.muni.fi.PA165.entity.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Jamik on 25.11.2015.
 */
@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventDao eventDao;

    @Autowired
    private SportDao sportDao;

    @Override
    public Event findEventById(Long id) {
        try{
            return eventDao.findById(id);
        }catch(Exception ex){
            throw new DataAccessException(ex);
        }
    }

    @Override
    public Event findEventByName(String name) {
        try{
            return eventDao.findByName(name);
        }catch(Exception ex){
            throw new DataAccessException(ex);
        }
    }

    @Override
    public Collection<Event> findEventsInDateRange(Date startDate, Date endDate) {
        try{
            return eventDao.findEventsInDateRange(startDate, endDate);
        }catch(Exception ex){
            throw new DataAccessException(ex);
        }
    }

    @Override
    public Collection<Event> getAllEvents() {
        try{
            return eventDao.findAll();
        }catch(Exception ex){
            throw new DataAccessException(ex);
        }
    }

    @Override
    public Long createEvent(Event evt) {
        try{
            eventDao.create(evt);
        }catch(Exception ex){
            throw new DataAccessException(ex);
        }
        return evt.getIdEvent();
    }

    @Override
    public void deleteEvent(Event evt) {
        try{
            eventDao.delete(evt);
        }catch(Exception ex){
            throw new DataAccessException(ex);
        }
    }

    @Override
    public void updateEvent(Event evt) {
        try{
            eventDao.update(evt);
        }catch(Exception ex){
            throw new DataAccessException(ex);
        }
    }

    @Override
    public void addSport(Event evt, Sport sport){
        if(!evt.getSports().contains(sport))
            evt.addSport(sport);
        // TODO: exception
    }

    @Override
    public void removeSport(Event evt, Sport sport) {
        try {
            evt.removeSport(sport);
            sportDao.delete(sport);
            //eventDao.update(evt);

        }catch(Exception ex){
        throw new DataAccessException(ex);
    }
    }
}
