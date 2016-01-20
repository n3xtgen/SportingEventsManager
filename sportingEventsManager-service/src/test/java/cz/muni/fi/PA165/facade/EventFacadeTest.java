package cz.muni.fi.PA165.facade;

import cz.muni.fi.PA165.Exceptions.DataAccessException;
import cz.muni.fi.PA165.dto.CreateEventDTO;
import cz.muni.fi.PA165.dto.CreateSportDTO;
import cz.muni.fi.PA165.dto.EventDTO;
import cz.muni.fi.PA165.dto.facade.EventFacade;
import cz.muni.fi.PA165.dto.facade.SportFacade;
import cz.muni.fi.PA165.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by Jamik on 17.12.2015.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class EventFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private EventFacade eventFacade;

    @Autowired
    SportFacade sportFacade;

    private CreateEventDTO evt1;
    private CreateEventDTO evt2;

    @BeforeClass
    public void setupData()  throws ServiceException {
        evt1 = new CreateEventDTO();
        evt1.setName("Pojidani parku");
        evt1.setDescription("Snist co nejvic parku za 10 min");
        Date sTime = new Date();
        sTime.setTime(sTime.getTime()+5000);
        evt1.setStartTime(sTime);
        Date eTime = new Date();
        eTime.setTime(eTime.getTime()+10000);
        evt1.setEndTime(eTime);

        // create second event
        evt2 = new CreateEventDTO();
        evt2.setName("Piti piva");
        evt2.setDescription("Vypit co nejvic piva za 10 min");
        sTime.setTime(sTime.getTime()+20000);
        evt2.setStartTime(sTime);
        eTime.setTime(eTime.getTime()+40000);
        evt2.setEndTime(eTime);

        eventFacade.addEvent(evt1);
        eventFacade.addEvent(evt2);
    }

    @Test
    public void shouldCreate(){
        Assert.assertNotEquals(evt1.getIdEvent(), null);
        Assert.assertEquals(eventFacade.findEventById(evt1.getIdEvent()).getIdEvent(), evt1.getIdEvent());
        Assert.assertEquals(eventFacade.findEventById(evt2.getIdEvent()).getIdEvent(), evt2.getIdEvent());
    }



    @Test
    public void shouldUpdate(){
        Assert.assertNotEquals(evt1.getIdEvent(), null);

        EventDTO event = eventFacade.findEventById(evt1.getIdEvent());
        event.setDescription("New updated desc");
        eventFacade.updateEvent(event);

        Assert.assertEquals(eventFacade.findEventById(event.getIdEvent()).getDescription(), "New updated desc");
    }


    @Test
    public void shouldFindById(){
        Assert.assertNotEquals(evt2.getIdEvent(), null);
        Assert.assertEquals(eventFacade.findEventById(evt2.getIdEvent()).getName(), evt2.getName());
        Assert.assertEquals(eventFacade.findEventById(evt2.getIdEvent()).getIdEvent(), evt2.getIdEvent());
    }


    @Test
    public void shouldFindByName(){
        Assert.assertNotEquals(evt2, null);
        Assert.assertEquals(eventFacade.findEventByName(evt2.getName()).getIdEvent(), evt2.getIdEvent());
    }


    @Test
    public void shouldGetAll(){
        Assert.assertEquals(eventFacade.getAllEvents().size(), 2);
    }


      @Test
      public void shouldAddSport(){
          CreateSportDTO sport = new CreateSportDTO();
          sport.setName("tenisss");
          Assert.assertNotEquals(evt1.getIdEvent(), null);
          sport.setEvent(evt1.getIdEvent());
          sportFacade.addNewSport(sport);

          eventFacade.addSport(evt1.getIdEvent(), sport.getIdSport());

          EventDTO event = eventFacade.findEventById(evt1.getIdEvent());
          Assert.assertTrue(event.getSports().contains(sportFacade.findSportById(sport.getIdSport())));
      }


    @Test
    public void shouldDeleteSport(){

    }

    @Test(expectedExceptions = DataAccessException.class)
    public void shouldDelete(){
        CreateEventDTO someEvent = new CreateEventDTO();
        someEvent.setName("Some stuff");
        someEvent.setDescription("Blaaah blaaaah");
        Date sTime = new Date();
        sTime.setTime(sTime.getTime()+5000);
        someEvent.setStartTime(sTime);
        Date eTime = new Date();
        eTime.setTime(eTime.getTime()+10000);
        someEvent.setEndTime(eTime);
        eventFacade.addEvent(someEvent);

        Assert.assertTrue(eventFacade.findEventById(someEvent.getIdEvent()) != null);

        eventFacade.deleteEvent(someEvent.getIdEvent());

        Assert.assertNull(eventFacade.findEventById(someEvent.getIdEvent()));
    }



}
