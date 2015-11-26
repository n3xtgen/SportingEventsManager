package cz.muni.fi.PA165.service.facade;

import cz.muni.fi.PA165.dto.CreateEventDTO;
import cz.muni.fi.PA165.dto.EventDTO;
import cz.muni.fi.PA165.dto.facade.EventFacade;
import cz.muni.fi.PA165.entity.Event;
import cz.muni.fi.PA165.service.BeanMappingService;
import cz.muni.fi.PA165.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.CollationElementIterator;
import java.util.Collection;

/**
 * Created by Jamik on 25.11.2015.
 */
public class EventFacadeImpl implements EventFacade {

    @Autowired
    private EventService eventService;

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
        eventService.createEvent(e);
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
}
