package cz.muni.fi.PA165.service.facade;

import cz.muni.fi.PA165.dto.CreateEventDTO;
import cz.muni.fi.PA165.dto.EventDTO;
import cz.muni.fi.PA165.dto.facade.EventFacade;
import cz.muni.fi.PA165.entity.Event;
import cz.muni.fi.PA165.service.BeanMappingService;
import cz.muni.fi.PA165.service.EventService;
import cz.muni.fi.PA165.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Jamik on 25.11.2015.
 */
@Service
@Transactional
public class EventFacadeImpl implements EventFacade {

    private final EventService eventService;

    private final SportService sportService;

    private final BeanMappingService beanMappingService;

    @Autowired
    public EventFacadeImpl(EventService eventService, SportService sportService, BeanMappingService beanMappingService) {
        this.eventService = eventService;
        this.sportService = sportService;
        this.beanMappingService = beanMappingService;
    }
    
    @Override
    public void addEvent(CreateEventDTO evt) {
        Event e = new Event();
        e.setName(evt.getName());
        e.setDescription(evt.getDescription());
        e.setStartTime(evt.getStartTime());
        e.setEndTime(evt.getEndTime());
        evt.setIdEvent(eventService.createEvent(e));
    }
    
    @Override
    public void updateEvent(EventDTO evt) {
        Event e = eventService.findEventById(evt.getIdEvent());
        e.setName(evt.getName());
        e.setDescription(evt.getDescription());
        e.setStartTime(evt.getStartTime());
        e.setEndTime(evt.getEndTime());
        eventService.updateEvent(e);
    }

    @Override
    public EventDTO findEventById(Long id) {
        Event e = eventService.findEventById(id);
        return (e == null ? null : beanMappingService.mapTo(e, EventDTO.class));
    }

    @Override
    public EventDTO findEventByName(String name){
        Event e = eventService.findEventByName(name);
        return (e == null ? null : beanMappingService.mapTo(e, EventDTO.class));
    }

    @Override
    public Collection<EventDTO> findEventsInDateRange(Date startDate, Date endDate) {
        return beanMappingService.mapTo(eventService.findEventsInDateRange(startDate, endDate), EventDTO.class);
    }

    @Override
    public Collection<EventDTO> getAllEvents(){
        return beanMappingService.mapTo(eventService.getAllEvents(), EventDTO.class);
    }

    @Override
    public boolean deleteEvent(Long eventId) {
        return eventService.deleteEvent(eventService.findEventById(eventId));
    }

    @Override
    public void addSport(Long eventId, Long sportId) {
        eventService.addSport(eventService.findEventById(eventId), sportService.findSportById(sportId));
    }

    @Override
    public boolean removeSport(Long eventId, Long sportId) {
        return eventService.removeSport(eventService.findEventById(eventId), sportService.findSportById(sportId));
    }
}
