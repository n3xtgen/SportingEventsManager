package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.Exceptions.DataAccessException;
import cz.muni.fi.PA165.dao.EventDao;
import cz.muni.fi.PA165.entity.Event;
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
    public void createEvent(Event evt) {
        try{
            eventDao.create(evt);
        }catch(Exception ex){
            throw new DataAccessException(ex);
        }
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


}
