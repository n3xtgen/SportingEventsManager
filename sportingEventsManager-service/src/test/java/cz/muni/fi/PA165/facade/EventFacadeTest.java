package cz.muni.fi.PA165.facade;

import cz.muni.fi.PA165.dto.CreateEventDTO;
import cz.muni.fi.PA165.dto.EventDTO;
import cz.muni.fi.PA165.dto.facade.EventFacade;
import cz.muni.fi.PA165.dto.facade.EventFacade;
import cz.muni.fi.PA165.entity.Event;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.service.BeanMappingService;
import cz.muni.fi.PA165.service.EventService;
import cz.muni.fi.PA165.service.EventService;
import cz.muni.fi.PA165.service.SportService;
import cz.muni.fi.PA165.service.config.ServiceConfiguration;
import cz.muni.fi.PA165.service.facade.EventFacadeImpl;
import cz.muni.fi.PA165.service.facade.EventFacadeImpl;
import java.util.ArrayList;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

import java.util.Date;
import java.util.List;
import org.mockito.Matchers;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Jamik on 17.12.2015.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class EventFacadeTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    private EventService eventService;

    @Mock
    private SportService sportService;

    @Mock
    private BeanMappingService beanMappingService;

    private EventFacade eventFacade;

    private CreateEventDTO createEvent1DTO;
    private EventDTO event1DTO;
    private Event event1;
    private EventDTO event2DTO;
    private Event event2;
    private List<EventDTO> eventDTOList;
    private List<Event> eventList;
    private Sport sport1;

    @BeforeClass
    public void setupData()  throws ServiceException {
        MockitoAnnotations.initMocks(this);
        
        eventFacade = new EventFacadeImpl(eventService, sportService, beanMappingService);
        
        Date eventStart = new Date();
        Date eventEnd = new Date();
        eventEnd.setTime(eventEnd.getTime()+30000);
        
        createEvent1DTO = new CreateEventDTO();
        createEvent1DTO.setName("Pojidani parku");
        createEvent1DTO.setDescription("Snist co nejvic parku za 10 min");
        createEvent1DTO.setStartTime(eventStart);
        createEvent1DTO.setEndTime(eventEnd);
        
        event1DTO = new EventDTO();
        event1DTO.setIdEvent(23L);
        event1DTO.setName(createEvent1DTO.getName());
        event1DTO.setDescription(createEvent1DTO.getDescription());
        event1DTO.setStartTime(createEvent1DTO.getStartTime());
        event1DTO.setEndTime(createEvent1DTO.getEndTime());
        
        event1 = new Event();
        event1.setIdEvent(event1DTO.getIdEvent());
        event1.setName(event1DTO.getName());
        event1.setDescription(event1DTO.getDescription());
        event1.setStartTime(event1DTO.getStartTime());
        event1.setEndTime(event1DTO.getEndTime());
        
        event2DTO = new EventDTO();
        event2DTO.setIdEvent(35L);
        event2DTO.setName("SUPER MEGA EVENT");
        event2DTO.setDescription("Nic vetsiho siroko daleko nehledejte");
        event2DTO.setStartTime(eventStart);
        event2DTO.setEndTime(eventEnd);
        
        event2 = new Event();
        event2.setIdEvent(event2DTO.getIdEvent());
        event2.setName(event2DTO.getName());
        event2.setDescription(event2DTO.getDescription());
        event2.setStartTime(event2DTO.getStartTime());
        event2.setEndTime(event2DTO.getEndTime());
        
        eventDTOList = new ArrayList<>();
        eventDTOList.add(event1DTO);
        eventDTOList.add(event2DTO);
        
        eventList = new ArrayList<>();
        eventList.add(event1);
        eventList.add(event2);
        
        sport1 = new Sport();
        sport1.setIdSport(77L);
        sport1.setName("Random sport");
        sport1.setEvent(event1);
        sport1.setStartTime(eventStart);
        sport1.setEndTime(eventEnd);
    }

    @Test
    public void addEventTest(){
        when(eventService.createEvent(Matchers.anyObject())).thenReturn(event1.getIdEvent());
        
        eventFacade.addEvent(createEvent1DTO);
        
        verify(eventService).createEvent(Matchers.anyObject());
    }



    @Test
    public void updateEventTest(){
        when(eventService.findEventById(event1DTO.getIdEvent())).thenReturn(event1);
        
        eventFacade.updateEvent(event1DTO);
        
        verify(eventService).updateEvent(event1);
    }


    @Test
    public void findEventByIdTest(){
        when(beanMappingService.mapTo(event1, EventDTO.class)).thenReturn(event1DTO);
        
        when(eventService.findEventById(event1.getIdEvent())).thenReturn(event1);
        Assert.assertEquals(eventFacade.findEventById(event1.getIdEvent()), event1DTO);
        
        when(eventService.findEventById(0L)).thenReturn(null);
        Assert.assertEquals(eventFacade.findEventById(0L), null);
    }


    @Test
    public void findEventByNameTest(){
        when(beanMappingService.mapTo(event1, EventDTO.class)).thenReturn(event1DTO);
        
        when(eventService.findEventByName(event1.getName())).thenReturn(event1);
        Assert.assertEquals(eventFacade.findEventByName(event1.getName()), event1DTO);
        
        when(eventService.findEventByName("neexistujici")).thenReturn(null);
        Assert.assertEquals(eventFacade.findEventByName("neexistujici"), null);
    }


    @Test
    public void findEventsInDateRangeTest(){
        when(eventService.findEventsInDateRange(event1.getStartTime(), event1.getEndTime())).thenReturn(eventList);
        when(beanMappingService.mapTo(eventList, EventDTO.class)).thenReturn(eventDTOList);
        
        Assert.assertEquals(eventFacade.findEventsInDateRange(event1.getStartTime(), event1.getEndTime()), eventDTOList);
    }


    @Test
    public void getAllEventsTest(){
        when(eventService.getAllEvents()).thenReturn(eventList);
        when(beanMappingService.mapTo(eventList, EventDTO.class)).thenReturn(eventDTOList);
        
        Assert.assertEquals(eventFacade.getAllEvents(), eventDTOList);
    }

    @Test
    public void deleteEventTest(){
        when(eventService.findEventById(event1.getIdEvent())).thenReturn(event1);
        
        when(eventService.deleteEvent(event1)).thenReturn(true);
        Assert.assertEquals(eventFacade.deleteEvent(event1.getIdEvent()), true);
        
        when(eventService.deleteEvent(event1)).thenReturn(false);
        Assert.assertEquals(eventFacade.deleteEvent(event1.getIdEvent()), false);
    }
    
    @Test
    public void addSportTest(){
        when(eventService.findEventById(event1.getIdEvent())).thenReturn(event1);
        when(sportService.findSportById(sport1.getIdSport())).thenReturn(sport1);
        
        eventFacade.addSport(event1.getIdEvent(), sport1.getIdSport());
                
        verify(eventService).addSport(event1, sport1);
    }
    
    @Test
    public void removeSportTest(){
        when(eventService.findEventById(event1.getIdEvent())).thenReturn(event1);
        when(sportService.findSportById(sport1.getIdSport())).thenReturn(sport1);
        when(eventService.removeSport(event1, sport1)).thenReturn(true);
        Assert.assertEquals(eventFacade.removeSport(event1.getIdEvent(), sport1.getIdSport()), true);
        
        when(eventService.findEventById(0L)).thenReturn(null);
        when(sportService.findSportById(0L)).thenReturn(null);
        when(eventService.removeSport(null, null)).thenReturn(false);
        Assert.assertEquals(eventFacade.removeSport(0L, 0L), false);
    }

}
