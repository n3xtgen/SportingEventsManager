package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.dto.EventDTO;
import cz.muni.fi.PA165.entity.Event;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jamik on 26.1.2016.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BeanMappingServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BeanMappingService beanMappingService;

    private Event evt1;
    private Sport sport1;
    private Sport sport2;
    private List<Event> events = new ArrayList<Event>();

    @BeforeClass
    public void setupData(){
        // create event
        evt1 = new Event();
        evt1.setName("Pojidani parku");
        evt1.setDescription("Snist co nejvic parku za 10 min");
        Date sTime = new Date();
        sTime.setTime(sTime.getTime()+5000);
        evt1.setStartTime(sTime);
        Date eTime = new Date();
        eTime.setTime(eTime.getTime()+10000);
        evt1.setEndTime(eTime);

        // create first sport
        sport1 = new Sport();
        sport1.setName("Squash");

        // create second sport
        sport2 = new Sport();
        sport2.setName("Basketball");

        evt1.addSport(sport1);
        evt1.addSport(sport2);

        events.add(evt1);
    }

    @Test
    public void shouldMapSports(){
        List<EventDTO> evt = beanMappingService.mapTo(events, EventDTO.class);
        Assert.assertEquals(evt.get(0).getSports().size(), 2);
    }

}
