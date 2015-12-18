package cz.muni.fi.PA165.facade;

import cz.muni.fi.PA165.dto.CreateEventDTO;
import cz.muni.fi.PA165.dto.CreateSportDTO;
import cz.muni.fi.PA165.dto.SportDTO;
import cz.muni.fi.PA165.dto.facade.EventFacade;
import cz.muni.fi.PA165.dto.facade.SportFacade;
import cz.muni.fi.PA165.service.config.ServiceConfiguration;
import org.hibernate.validator.cfg.defs.AssertFalseDef;
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
public class SportFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SportFacade sportFacade;

    @Autowired
    private EventFacade eventFacade;

    private CreateSportDTO sport1;
    private CreateSportDTO sport2;
    private CreateEventDTO evt1;

    @BeforeClass
    public void setupData(){
        sport1 = new CreateSportDTO();
        sport1.setName("Hockey");

        sport2 = new CreateSportDTO();
        sport2.setName("Football");

        evt1 = new CreateEventDTO();
        evt1.setName("Pojidani parku");
        evt1.setDescription("Snist co nejvic parku za 10 min");
        Date sTime = new Date();
        sTime.setTime(sTime.getTime()+5000);
        evt1.setStartTime(sTime);
        Date eTime = new Date();
        eTime.setTime(eTime.getTime()+10000);
        evt1.setEndTime(eTime);

        eventFacade.addEvent(evt1);

        sport1.setEvent(evt1.getIdEvent());
        sport2.setEvent(evt1.getIdEvent());

        sportFacade.addNewSport(sport1);
        sportFacade.addNewSport(sport2);
    }

    @Test
    public void shouldCreate(){
        Assert.assertEquals(sportFacade.findSportById(sport1.getIdSport()).getIdSport(), sport1.getIdSport());
        Assert.assertEquals(sportFacade.findSportById(sport2.getIdSport()).getIdSport(), sport2.getIdSport());
        Assert.assertEquals(sportFacade.getAllSports().size(),2);
    }

    @Test
    public void shouldFindById(){
        Assert.assertEquals(sportFacade.findSportById(sport1.getIdSport()).getIdSport(), sport1.getIdSport());
    }

    @Test
    public void shouldFindByName(){
        Assert.assertEquals(sportFacade.findSportByName(sport2.getName()).getIdSport(), sport2.getIdSport());
    }

    @Test
    public void shouldUpdate(){
        SportDTO sport = sportFacade.findSportById(sport1.getIdSport());

        sport.setName("Updated name");
        sportFacade.updateSport(sport);

        Assert.assertEquals(sportFacade.findSportByName("Updated name").getIdSport(), sport1.getIdSport());

    }

    @Test
    public void shouldGetAll(){
        Assert.assertEquals(sportFacade.getAllSports().size(), 2);
    }
}
