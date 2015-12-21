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

    @Autowired
    private EventService eventService;

    @Autowired
    private SportService sportService;

    @Autowired
    private BeanMappingService beanMappingService;

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
    public void addEvent(CreateEventDTO evt) {
        Event e = new Event();
        e.setName(evt.getName());
        e.setDescription(evt.getDescription());
        e.setStartTime(evt.getStartTime());
        e.setEndTime(evt.getEndTime());
        evt.setIdEvent(eventService.createEvent(e));
    }

    @Override
    public void deleteEvent(Long eventId) {
        System.out.println("public void deleteEvent(Long eventId)");
        eventService.deleteEvent(eventService.findEventById(eventId));
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
    public void addSport(Long eventId, Long sportId) {
        eventService.addSport(eventService.findEventById(eventId), sportService.findSportById(sportId));
    }

    @Override
    public void removeSport(Long eventId, Long sportId) {
        System.out.println(" removeSport(Long eventId, Long sportId) = " + eventId + " , " + sportId);
        eventService.removeSport(eventService.findEventById(eventId), sportService.findSportById(sportId));
    }
}
