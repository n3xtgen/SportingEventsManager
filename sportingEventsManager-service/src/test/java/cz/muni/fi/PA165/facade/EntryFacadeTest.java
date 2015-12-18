package cz.muni.fi.PA165.facade;

import cz.muni.fi.PA165.dto.*;
import cz.muni.fi.PA165.dto.facade.EntryFacade;
import cz.muni.fi.PA165.dto.facade.EventFacade;
import cz.muni.fi.PA165.dto.facade.SportFacade;
import cz.muni.fi.PA165.dto.facade.UserFacade;
import cz.muni.fi.PA165.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Jamik on 17.12.2015.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class EntryFacadeTest extends AbstractTestNGSpringContextTests  {

    @Autowired
    private EntryFacade entryFacade;

    @Autowired
    private EventFacade eventFacade;

    @Autowired
    private SportFacade sportFacade;

    @Autowired
    private UserFacade userFacade;

    private CreateEntryDTO someEntry;

    private CreateEventDTO someEvent;

    private CreateSportDTO someSport;

    private CreateUserDTO someUser;

    @BeforeClass
    public void setupData(){
        // create event
        someEvent = new CreateEventDTO();
        someEvent.setName("Some event");
        someEvent.setDescription("Some random sports");
        Date sTime = new Date();
        sTime.setTime(sTime.getTime()+5000);
        someEvent.setStartTime(sTime);
        Date eTime = new Date();
        eTime.setTime(eTime.getTime()+10000);
        someEvent.setEndTime(eTime);
        eventFacade.addEvent(someEvent);

        // create sport
        someSport = new CreateSportDTO();
        someSport.setName("basketball");
        someSport.setEvent(someEvent.getIdEvent());
        sportFacade.addNewSport(someSport);

        // create user
        someUser = new CreateUserDTO();
        someUser.setName("Petr");
        someUser.setSurname("Filek");
        someUser.setPassword("askldjlsdfjk");
        someUser.setEmail("Petr@filek.cz");
        userFacade.registerUser(someUser);

        // create entry
        someEntry = new CreateEntryDTO();
        someEntry.setSportId(someSport.getIdSport());
        someEntry.setSportsmanId(someUser.getId());

        entryFacade.registerEntry(someEntry);
    }

    @Test
    public void shouldCreate(){
        Assert.assertEquals(entryFacade.findEntryById(someEntry.getEntryId()).getIdEntry(), someEntry.getEntryId());
    }

    @Test
    public void shouldUpdate(){
        EntryDTO entry = entryFacade.findEntryById(someEntry.getEntryId());
        int pos = entry.getPosition();

        entry.setPosition(10);
        entryFacade.updateEntry(entry);

        Assert.assertNotEquals(entryFacade.findEntryById(someEntry.getEntryId()).getPosition(), pos);
    }

    @Test
    public void shouldDelete(){

    }

    @Test
    public void shouldFindById(){
        Assert.assertEquals(entryFacade.findEntryById(someEntry.getEntryId()).getIdEntry(), someEntry.getEntryId());
    }

    @Test
    public void shouldFindBySportId(){
        EntryDTO e = entryFacade.findEntryById(someEntry.getEntryId());
        Assert.assertNotNull(entryFacade.findEntriesBySportId(someSport.getIdSport()));
        Assert.assertNotNull(e);
        Collection<EntryDTO> entries = entryFacade.findEntriesBySportId(someSport.getIdSport());
        Assert.assertTrue(entries.size() > 0);

        Assert.assertTrue(entries.contains(e));
    }

    @Test
    public void shouldFindByUserId(){
        EntryDTO e = entryFacade.findEntryById(someEntry.getEntryId());
        Assert.assertTrue(entryFacade.findEntriesBySportsmanId(someUser.getId()).contains(e));
    }

    @Test
    public void shouldFindBySportAndUserId(){
        EntryDTO e = entryFacade.findEntryById(someEntry.getEntryId());
        Assert.assertEquals(entryFacade.findEntryBySportsmanAndSportId(someSport.getIdSport(), someUser.getId()), e);
    }
}
