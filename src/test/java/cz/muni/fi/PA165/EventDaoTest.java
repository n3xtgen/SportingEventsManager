package cz.muni.fi.PA165;

import cz.muni.fi.PA165.dao.EventDao;
import cz.muni.fi.PA165.dao.SportDao;
import cz.muni.fi.PA165.entity.Event;
import cz.muni.fi.PA165.entity.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jamik
 */
@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class EventDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private SportDao sportDao;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * create 2 events, save them and then try to get them from DB
     */
    @Test
    public void tryToFindAllEvents()
    {
        // create 2 events
        Event evt1 = new Event();
        Event evt2 = new Event();
        evt1.setName("football");
        evt2.setName("hockey");
        evt1.setDescription("blah");
        evt2.setDescription("nah");
        evt1.setStartTime(new Date());
        evt1.setEndTime(new Date());
        evt2.setStartTime(new Date());
        evt2.setEndTime(new Date());
        // save them
        eventDao.create(evt1);
        eventDao.create(evt2);
        // get from DB
        List<Event> eventsFromDB = eventDao.findAll();
        // check stuff
        Assert.assertEquals(eventsFromDB.size(), 2);
        Assert.assertTrue(eventsFromDB.contains(evt1));
        Assert.assertTrue(eventsFromDB.contains(evt2));
    }

    /**
     * create event, save to DB, get from DB using its ID
     */
    @Test
    public void tryToFindById(){
        // create event
        Event evt1 = new Event();
        evt1.setName("football");
        evt1.setDescription("blah");
        evt1.setStartTime(new Date());
        evt1.setEndTime(new Date());
        // save to DB
        eventDao.create(evt1);
        // check stuff
        Assert.assertEquals(eventDao.findById(evt1.getIdEvent()), evt1);
    }

    /**
     * create 2 events, save to DB, delete 1 of them, get all from DB
     */
    @Test
    public void tryToDelete(){
        // create 2 events
        Event evt1 = new Event();
        Event evt2 = new Event();
        evt1.setName("football");
        evt2.setName("hockey");
        evt1.setDescription("blah");
        evt2.setDescription("nah");
        evt1.setStartTime(new Date());
        evt1.setEndTime(new Date());
        evt2.setStartTime(new Date());
        evt2.setEndTime(new Date());
        // save to DB
        eventDao.create(evt1);
        eventDao.create(evt2);
        // delete
        eventDao.delete(evt1);
        // get from DB
        List<Event> eventsFromDB = eventDao.findAll();
        // check stuff
        Assert.assertEquals(eventsFromDB.size(), 1);
        Assert.assertTrue(eventsFromDB.contains(evt2));
    }

    /**
     * try to save a new event without setting up its name -> should cause constraints violation
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void tryConstraintsViolation(){
        Event evt = new Event();
        eventDao.create(evt);
    }

    /**
     * try to save 2 events with the same name -> should cause exception about breaking the uniqueness
     */
    @Test(expectedExceptions = PersistenceException.class)
    public void tryUniquenessViolation(){
        Event evt1 = new Event();
        Event evt2 = new Event();
        evt1.setName("seemsTheSameToMe");
        evt2.setName("seemsTheSameToMe");
        evt1.setDescription("blah");
        evt2.setDescription("nah");
        evt1.setStartTime(new Date());
        evt1.setEndTime(new Date());
        evt2.setStartTime(new Date());
        evt2.setEndTime(new Date());
        // save to DB
        eventDao.create(evt1);
        eventDao.create(evt2);
    }

    @Test
    public void tryToUpdateEvent(){
        // create event
        Event evt1 = new Event();
        evt1.setName("football");
        evt1.setDescription("blah");
        evt1.setStartTime(new Date());
        evt1.setEndTime(new Date());
        // save to DB
        eventDao.create(evt1);
        // udpate some data
        evt1.setDescription("naaah");
        Long evt1Id = evt1.getIdEvent();
        // update in DB
        eventDao.update(evt1);

        evt1 = eventDao.findById(evt1Id);
        // check stuff
        Assert.assertEquals(evt1.getDescription(), "naaah");
    }

    @Test
    public void tryReferenceToSport(){
        Event evt = new Event();
        evt.setName("football");
        evt.setDescription("blah");
        evt.setStartTime(new Date());
        evt.setEndTime(new Date());

        Set<Sport> someSports = new HashSet<Sport>();
        Sport spt1 = new Sport();
        Sport spt2 = new Sport();

        spt1.setName("hockey");
        spt2.setName("football");

        someSports.add(spt1);
        someSports.add(spt2);

        evt.setSportTypes(someSports);
        Assert.assertEquals(evt.getSportTypes().size(), 2);
        // save sports to DB
        sportDao.create(spt1);
        sportDao.create(spt2);
        // save the event to DB
        eventDao.create(evt);

        Event retEvt = eventDao.findById(evt.getIdEvent());

        // check event
        Assert.assertEquals(retEvt.getName(), evt.getName());
        Assert.assertNotNull(retEvt.getSportTypes());
        // check number of references
        Assert.assertEquals(retEvt.getSportTypes().size(), 2);
        // check reference to Sports
        Assert.assertTrue(retEvt.getSportTypes().containsAll(someSports));
    }
}
