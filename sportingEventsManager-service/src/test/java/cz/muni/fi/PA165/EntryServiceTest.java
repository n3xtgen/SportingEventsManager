package cz.muni.fi.PA165;

import cz.muni.fi.PA165.dao.EntryDao;
import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Sportsman;
import cz.muni.fi.PA165.service.EntryService;
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

import java.util.Date;

import static org.mockito.Mockito.when;

/**
 * @author n3xtgen
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class EntryServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private EntryDao entryDao;

    @Autowired
    @InjectMocks
    private EntryService entryService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Sport sport;

    private Sportsman sportsman1;
    private Sportsman sportsman2;

    private Entry entry1;
    private Entry entry2;

    @BeforeMethod
    public void prepareTestData() {
        sport = new Sport();
        sport.setName("Sport 1");

        sportsman1 = new Sportsman();
        sportsman1.setName("Pepa");

        sportsman2 = new Sportsman();
        sportsman2.setName("Stefan");

        entry1 = new Entry();
        entry1.setSport(sport);
        entry1.setSportsman(sportsman1);
        entry1.setEntryState(Entry.EntryState.REGISTERED);

        entry2 = new Entry();
        entry2.setSport(sport);
        entry2.setSportsman(sportsman2);
        entry2.setEntryState(Entry.EntryState.REGISTERED);
    }

    @Test
    public void shouldCreateEvent(){
        entryService.createEntry(entry1);
        entryService.createEntry(entry2);
    }


    @Test
    public void shouldFindById(){
        long entryId = 1;
        when(entryDao.findById(entryId)).thenReturn(entry1);

        Assert.assertEquals(entryService.findEntryById(entryId), entry1);
    }
}
