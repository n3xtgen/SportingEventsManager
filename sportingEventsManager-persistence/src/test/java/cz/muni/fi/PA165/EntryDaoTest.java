package cz.muni.fi.PA165;

import cz.muni.fi.PA165.dao.EntryDao;
import cz.muni.fi.PA165.dao.EventDao;
import cz.muni.fi.PA165.dao.SportDao;
import cz.muni.fi.PA165.dao.SportsmanDao;
import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Event;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Sportsman;
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
    private SportsmanDao sportsmanDao;

    @Autowired
    private EntryDao entryDao;

    @PersistenceContext
    private EntityManager em;

    private Event event;

    private Sport sport;

    private Sportsman sportsman1;
    private Sportsman sportsman2;

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
        sportDao.create(sport);

        sportsman1 = new Sportsman();
        sportsman1.setName("Jmeno");
        sportsman1.setSurname("Prijmeni");
        sportsman1.setEmail("nikdo@nikde.nic");
        sportsman1.setPasswordHash("hash");
        sportsmanDao.create(sportsman1);

        sportsman2 = new Sportsman();
        sportsman2.setName("Dvojka");
        sportsman2.setSurname("Novakova");
        sportsman2.setEmail("666@111.nic");
        sportsman2.setPasswordHash("hash");
        sportsmanDao.create(sportsman2);
    }

    @Test
    public void shouldfindAll() {
        Entry ent1 = new Entry();
        ent1.setSport(sport);
        ent1.setSportsman(sportsman1);
        ent1.setEntryState(Entry.EntryState.REGISTERED);
        Entry ent2 = new Entry();
        ent2.setSport(sport);
        ent2.setSportsman(sportsman2);
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
        ent1.setSportsman(sportsman1);
        ent1.setEntryState(Entry.EntryState.REGISTERED);
        Entry ent2 = new Entry();
        ent2.setSport(sport);
        ent2.setSportsman(sportsman2);
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
        ent1.setSportsman(sportsman1);
        ent1.setEntryState(Entry.EntryState.REGISTERED);
        Entry ent2 = new Entry();
        ent2.setSport(sport);
        ent2.setSportsman(sportsman2);
        ent2.setEntryState(Entry.EntryState.REGISTERED);

        entryDao.create(ent1);
        entryDao.create(ent2);

        List<Entry> entries = entryDao.findBySportsman(sportsman2);

        Assert.assertEquals(entries.size(), 1);
        Assert.assertTrue(entries.contains(ent2));
    }
}
