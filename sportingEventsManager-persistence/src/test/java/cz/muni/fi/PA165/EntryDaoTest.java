package cz.muni.fi.PA165;

import cz.muni.fi.PA165.dao.EntryDao;
import cz.muni.fi.PA165.dao.EventDao;
import cz.muni.fi.PA165.dao.SportDao;
import cz.muni.fi.PA165.dao.UserDao;
import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Event;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Usr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * @author n3xtgen
 */
@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class EntryDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private SportDao sportDao;

    @Autowired
    private UserDao sportsmanDao;

    @Autowired
    private EntryDao entryDao;

    @PersistenceContext
    private EntityManager em;

    private Event event;

    private Sport sport;

    private Usr usr1;
    private Usr usr2;

    @BeforeMethod
    public void prepareEvent() {
        event = new Event();
        event.setName("Event 1");
        event.setDescription("Event description.");
        event.setStartTime(new Date());
        event.setEndTime(new Date());
        eventDao.create(event);

        sport = new Sport();
        sport.setName("Sport 1");
        sport.setEvent(event);
        sport.setEndTime(new Date(946771200));
        sport.setStartTime(new Date(946684800));
        sportDao.create(sport);

        usr1 = new Usr();
        usr1.setName("Jmeno");
        usr1.setSurname("Prijmeni");
        usr1.setEmail("nikdo@nikde.nic");
        usr1.setPasswordHash("hash");
        sportsmanDao.create(usr1);

        usr2 = new Usr();
        usr2.setName("Dvojka");
        usr2.setSurname("Novakova");
        usr2.setEmail("666@111.nic");
        usr2.setPasswordHash("hash");
        sportsmanDao.create(usr2);
    }

    @Test
    public void shouldfindAll() {
        Entry ent1 = new Entry();
        ent1.setSport(sport);
        ent1.setUsr(usr1);
        ent1.setEntryState(Entry.EntryState.REGISTERED);
        Entry ent2 = new Entry();
        ent2.setSport(sport);
        ent2.setUsr(usr2);
        ent2.setEntryState(Entry.EntryState.REGISTERED);

        entryDao.create(ent1);
        entryDao.create(ent2);

        List<Entry> entries = entryDao.findAll();

        Assert.assertEquals(entries.size(), 2);
        Assert.assertTrue(entries.contains(ent1));
        Assert.assertTrue(entries.contains(ent2));
    }

    @Test
    public void shouldfindBySport() {
        Entry ent1 = new Entry();
        ent1.setSport(sport);
        ent1.setUsr(usr1);
        ent1.setEntryState(Entry.EntryState.REGISTERED);
        Entry ent2 = new Entry();
        ent2.setSport(sport);
        ent2.setUsr(usr2);
        ent2.setEntryState(Entry.EntryState.REGISTERED);

        entryDao.create(ent1);
        entryDao.create(ent2);

        List<Entry> entries = entryDao.findBySport(sport);

        Assert.assertEquals(entries.size(), 2);
        Assert.assertTrue(entries.contains(ent1));
        Assert.assertTrue(entries.contains(ent2));
    }

    @Test
    public void shouldfindBySportsman() {
        Entry ent1 = new Entry();
        ent1.setSport(sport);
        ent1.setUsr(usr1);
        ent1.setEntryState(Entry.EntryState.REGISTERED);
        Entry ent2 = new Entry();
        ent2.setSport(sport);
        ent2.setUsr(usr2);
        ent2.setEntryState(Entry.EntryState.REGISTERED);

        entryDao.create(ent1);
        entryDao.create(ent2);

        List<Entry> entries = entryDao.findByUser(usr2);

        Assert.assertEquals(entries.size(), 1);
        Assert.assertTrue(entries.contains(ent2));
    }
}
