package cz.muni.fi.PA165.sampledata;

import cz.muni.fi.PA165.entity.Event;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Sportsman;
import cz.muni.fi.PA165.service.EventService;
import cz.muni.fi.PA165.service.SportService;
import cz.muni.fi.PA165.service.SportsmanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;


@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private SportsmanService sportsmanService;

    @Autowired
    private EventService eventService;

    @Autowired
    private SportService sportService;
   
    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        Sportsman sp = sportsman("password", "admin","bbb","email@mail.com");

        createSomeEvents();

    }

  
    private Sportsman sportsman(String password, String name, String surname, String email) {
        Sportsman s = new Sportsman();
        s.setName(name);
        s.setSurname(surname);
        s.setEmail(email);
       
        sportsmanService.registerSportsman(s, password);
        log.debug("register sportsman" + s);
        return s;
    }

   private void createSomeEvents(){
       // create 2 events
       Event evt1 = new Event();
       Event evt2 = new Event();
       evt1.setName("Event cislo 1");
       evt2.setName("Event cislo 2");
       evt1.setDescription("blah");
       evt2.setDescription("nah");

       // evt1 dates
       // set start date
       Date dStart1 = new Date();
       dStart1.setTime(dStart1.getTime()+2000);
       evt1.setStartTime(dStart1);
       // set end date
       Date dEnd1 = new Date();
       dEnd1.setTime(dEnd1.getTime() + 5000);
       evt1.setEndTime(dEnd1);

       // set start date
       Date dStart2 = new Date();
       dStart2.setTime(dStart2.getTime()+6000);
       evt2.setStartTime(dStart2);
       // set end date
       Date dEnd2 = new Date();
       dEnd2.setTime(dEnd2.getTime()+8000);
       evt2.setEndTime(dEnd2);

       // add some sports
       Sport sport1 = new Sport();
       sport1.setName("Squash");
       Sport sport2 = new Sport();
       sport2.setName("Basketball");

       Sport sport3 = new Sport();
       sport3.setName("Football");
       Sport sport4 = new Sport();
       sport4.setName("Hockey");
       Sport sport5 = new Sport();
       sport5.setName("Sprint");
       Sport sport6 = new Sport();
       sport6.setName("Long-distance run");

       sport1.setEvent(evt1);
       sport2.setEvent(evt1);
       sport3.setEvent(evt2);
       sport4.setEvent(evt2);
       sport5.setEvent(evt2);
       sport6.setEvent(evt2);

       evt1.addSport(sport1);
       evt1.addSport(sport2);

       evt2.addSport(sport3);
       evt2.addSport(sport4);
       evt2.addSport(sport5);
       evt2.addSport(sport6);


       // save them
       eventService.createEvent(evt1);
       eventService.createEvent(evt2);

       sportService.addNewSport(sport1);
       sportService.addNewSport(sport2);
       sportService.addNewSport(sport3);
       sportService.addNewSport(sport4);
       sportService.addNewSport(sport5);
       sportService.addNewSport(sport6);






   }
    
}
