package cz.muni.fi.PA165;

import cz.muni.fi.PA165.Exceptions.DataAccessException;
import cz.muni.fi.PA165.dao.EventDao;
import cz.muni.fi.PA165.entity.Event;
import cz.muni.fi.PA165.service.EventService;
import cz.muni.fi.PA165.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by Jamik on 26.11.2015.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class EventServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private EventDao eventDao;

    @Autowired
    @InjectMocks
    private EventService eventService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Event evt1;
    private Event evt2;

    /**
     * Prepare testing data
     */
    @BeforeMethod
    public void prepareTestData() {
        // create first event
        evt1 = new Event();
        evt1.setName("Pojidani parku");
        evt1.setDescription("Snist co nejvic parku za 10 min");
        Date sTime = new Date();
        sTime.setTime(sTime.getTime()+5000);
        evt1.setStartTime(sTime);
        Date eTime = new Date();
        eTime.setTime(eTime.getTime()+10000);
        evt1.setEndTime(eTime);

        // create second event
        evt2 = new Event();
        evt2.setName("Piti piva");
        evt2.setDescription("Vypit co nejvic piva za 10 min");
        sTime.setTime(sTime.getTime()+20000);
        evt2.setStartTime(sTime);
        eTime.setTime(eTime.getTime()+40000);
        evt2.setEndTime(eTime);
    }

    @Test
    public void shouldCreateEvent(){
        eventService.createEvent(evt1);
        eventService.createEvent(evt2);

        when(eventDao.findById(evt1.getIdEvent())).thenReturn(evt1);
        when(eventDao.findById(evt2.getIdEvent())).thenReturn(evt2);

        Assert.assertEquals(eventService.findEventById(evt1.getIdEvent()), evt1);
        Assert.assertEquals(eventService.findEventById(evt2.getIdEvent()), evt2);
    }


    /**
     * Try to find Event by its ID
     */
    @Test
    public void shouldFindById(){
        long evtId = 1;
        when(eventDao.findById(evtId)).thenReturn(evt1);

        Assert.assertEquals(eventService.findEventById(evtId), evt1);
    }

    /**
     * Try to find Event by its name
     */
    @Test
    public void shouldFindByName(){
        String eName = "Piti piva";
        when(eventDao.findByName(eName)).thenReturn(evt2);

        Assert.assertEquals(eventService.findEventByName(eName), evt2);
    }

    /**
     * Try to find all Events
     */
    @Test
    public void shouldFindAllEvents(){
        List<Event> allEvents = new ArrayList<Event>();
        allEvents.add(evt1);
        allEvents.add(evt2);

        when(eventDao.findAll()).thenReturn(allEvents);

        Assert.assertEquals(eventService.getAllEvents(), allEvents);
    }

    /**
     * Try to find events in a specified date range.
     * The sample data contain event in range (now + 5000) - (now + 10000) and (now + 20000) - (now + 40000),
     * so when we try to find events in range (now + 3000) - (now + 12000) we should get a list with the first event
     */
    @Test
    public void shouldFindEventsInRange(){
        List<Event> evtsInRange = new ArrayList<Event>();
        evtsInRange.add(evt1);

        Date d1 = new Date();
        Date d2 = new Date();
        d1.setTime(d1.getTime()+3000);
        d2.setTime(d2.getTime()+12000);

        when(eventDao.findEventsInDateRange(d1, d2)).thenReturn(evtsInRange);

        Assert.assertEquals(eventService.findEventsInDateRange(d1, d2), evtsInRange);
    }

}
