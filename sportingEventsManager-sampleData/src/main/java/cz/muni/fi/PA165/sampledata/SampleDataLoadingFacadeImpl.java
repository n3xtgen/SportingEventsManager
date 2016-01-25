package cz.muni.fi.PA165.sampledata;

import cz.muni.fi.PA165.dao.EntryDao;
import cz.muni.fi.PA165.dao.EventDao;
import cz.muni.fi.PA165.dao.SportDao;
import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Event;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Usr;
import cz.muni.fi.PA165.service.EntryService;
import cz.muni.fi.PA165.service.EventService;
import cz.muni.fi.PA165.service.SportService;
import cz.muni.fi.PA165.service.UserService;
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
    private UserService userService;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private SportDao sportDao;

    @Autowired
    private EntryDao entryDao;
   
    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        createSomeUsers();
        createSomeEvents();
        createSomeSports();
        createSomeEntries();
    }

  
    private Usr addUser(String password, String name, String surname, String email, boolean isAdmin) {
        Usr newUser = new Usr();
        newUser.setAdmin(isAdmin);
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setEmail(email);
       
        userService.registerSportsman(newUser, password);
        return newUser;
    }
    
    private Event addEvent(String name, String description, Date startDate, Date endDate) {
        Event newEvent = new Event();
        newEvent.setName(name);
        newEvent.setDescription(description);
        newEvent.setStartTime(startDate);
        newEvent.setEndTime(endDate);
       
        eventDao.create(newEvent);
        return newEvent;
    }
    
    private Sport addSport(String name, Date startDate, Date endDate, Event event) {
        Sport newSport = new Sport();
        newSport.setName(name);
        newSport.setStartTime(startDate);
        newSport.setEndTime(endDate);
        newSport.setEvent(event);
       
        sportDao.create(newSport);
        return newSport;
    }
    
    private Entry addEntry(Usr user, Sport sport, Entry.EntryState entryState, int position, Date time) {
        Entry newEntry = new Entry();
        newEntry.setUsr(user);
        newEntry.setSport(sport);
        newEntry.setEntryState(entryState);
        newEntry.setPosition(position);
        newEntry.setTime(time);
       
        entryDao.create(newEntry);
        return newEntry;
    }
    
    Date timeNowWithoutSecs;
    // Vrati aktualni cas orezany o sekundy pro lepsi cteni a pricte k nemu zadane minuty.
    private Date nowPlusMinutes(long minutes) {
        if (timeNowWithoutSecs == null) {
            timeNowWithoutSecs = new Date();
            timeNowWithoutSecs.setMinutes(0);
        }
        
        return new Date(timeNowWithoutSecs.getTime() + (minutes * 60000));
    }
    
    // Vrati cas pro vysledek se zadanyma minutama, sekundama a  milisekundama
    private Date resultTime(int minutes, int seconds, int miliseconds) {
        Date time = new Date(0, 0, 0, 0, minutes, seconds);
        time.setTime(time.getTime() + miliseconds);
        return time;
    }
    
    Usr user1;
    Usr user2;
    Usr user3;
    Usr user4;
    Usr user5;
    Usr user6;
    Usr user7;
    Usr user8;
    Usr user9;
    Usr user10;
    Usr user11;
    Usr user12;
    Usr user13;
    Usr user14;
    Usr user15;
    Usr user16;
    Usr user17;
    
    private void createSomeUsers()
    {
        user1 = addUser("password", "Adminator","von Root","admin@email.com",true); // admin
        
        user2 = addUser("password", "Usain", "Thunder", "user4@email.com",false); // bezec
        user3 = addUser("password", "Emil", "Floodman", "user3@email.com",false); // bezec
        user4 = addUser("password", "Paula", "Radcliffe", "user2@email.com",false); // bezec
        user5 = addUser("password", "Roger", "Canister", "user5@email.com",false); // bezec
        user6 = addUser("password", "Jesse", "Owens", "user6@email.com",false); // bezec
        user7 = addUser("password", "Kathrine", "Switzer", "user7@email.com",false); // bezec
        
        user8 = addUser("password", "Missy", "Franklin", "user8@email.com",false); // plavec
        user9 = addUser("password", "David", "Nolan", "user9@email.com",false); // plavec
        user10 = addUser("password", "Grant", "Hackett", "user10@email.com",false); // plavec
        user11 = addUser("password", "Michael", "Phelps", "user11@email.com",false); // plavec
        user12 = addUser("password", "Krisztina", "Egerszegi", "user12@email.com",false); // plavec
        
        user13 = addUser("password", "Eddy", "Merckx", "user13@email.com",false); // cyklista
        user14 = addUser("password", "Jan", "Ullrich", "user14@email.com",false); // cyklista
        user15 = addUser("password", "Fausto", "Coppi", "user15@email.com",false); // cyklista
        user16 = addUser("password", "Miguel", "Indurain", "user16@email.com",false); // cyklista
        user17 = addUser("password", "Sean", "Kelly", "user17@email.com",false); // cyklista
    }

    Event event1;
    Event event2;
    
    private void createSomeEvents()
    {
        event1 = addEvent("Running Event",
                "Add some description. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                nowPlusMinutes(-30), nowPlusMinutes(45));
        
        event2 = addEvent("Simultaneous Triatlon Event",
                "Add some description. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                nowPlusMinutes(0), nowPlusMinutes(50));
    }
    
    Sport sport1;
    Sport sport2;
    Sport sport3;
    Sport sport4;
    Sport sport5;
    Sport sport6;
    
    private void createSomeSports()
    {
        sport1 = addSport("Run - 100m", nowPlusMinutes(-25), nowPlusMinutes(-15), event1);
        sport2 = addSport("Run - 800m", nowPlusMinutes(-5), nowPlusMinutes(10), event1);
        sport3 = addSport("Run - 2000m", nowPlusMinutes(20), nowPlusMinutes(40), event1);
        
        sport4 = addSport("Run - 1,5km", nowPlusMinutes(5), nowPlusMinutes(20), event2);
        sport5 = addSport("Swimming - 750m", nowPlusMinutes(10), nowPlusMinutes(30), event2);
        sport6 = addSport("Cycling - 20km", nowPlusMinutes(15), nowPlusMinutes(45), event2);
    }

    
    
    private void createSomeEntries(){
        
        //Event 1
        addEntry(user2, sport1, Entry.EntryState.FINISHED, 1, resultTime(0, 9, 270));
        addEntry(user3, sport1, Entry.EntryState.FINISHED, 2, resultTime(0, 9, 634));
        addEntry(user4, sport1, Entry.EntryState.FINISHED, 3, resultTime(0, 10, 512));
        addEntry(user5, sport1, Entry.EntryState.FINISHED, 4, resultTime(0, 10, 900));
        addEntry(user6, sport1, Entry.EntryState.FINISHED, 5, resultTime(0, 11, 505));
        addEntry(user7, sport1, Entry.EntryState.FINISHED, 6, resultTime(0, 12, 333));
        
        addEntry(user2, sport2, Entry.EntryState.FINISHED, 1, resultTime(1, 02, 333));
        addEntry(user3, sport2, Entry.EntryState.FINISHED, 2, resultTime(1, 04, 834));
        addEntry(user4, sport2, Entry.EntryState.REGISTERED, 0, null);
        addEntry(user5, sport2, Entry.EntryState.REGISTERED, 0, null);
        addEntry(user6, sport2, Entry.EntryState.REGISTERED, 0, null);
        addEntry(user7, sport2, Entry.EntryState.DISQUALIFIED, 0, null);
        
        addEntry(user2, sport3, Entry.EntryState.REGISTERED, 0, null);
        addEntry(user3, sport3, Entry.EntryState.REGISTERED, 0, null);
        addEntry(user4, sport3, Entry.EntryState.REGISTERED, 0, null);
        
        //Event 2
        addEntry(user5, sport4, Entry.EntryState.REGISTERED, 0, null);
        addEntry(user6, sport4, Entry.EntryState.REGISTERED, 0, null);
        addEntry(user7, sport4, Entry.EntryState.REGISTERED, 0, null);
        
        addEntry(user8, sport5, Entry.EntryState.REGISTERED, 0, null);
        addEntry(user9, sport5, Entry.EntryState.REGISTERED, 0, null);
        addEntry(user10, sport5, Entry.EntryState.REGISTERED, 0, null);
        addEntry(user11, sport5, Entry.EntryState.REGISTERED, 0, null);
        addEntry(user12, sport5, Entry.EntryState.REGISTERED, 0, null);
        
        addEntry(user13, sport6, Entry.EntryState.REGISTERED, 0, null);
        addEntry(user14, sport6, Entry.EntryState.REGISTERED, 0, null);
        addEntry(user15, sport6, Entry.EntryState.REGISTERED, 0, null);
        addEntry(user16, sport6, Entry.EntryState.REGISTERED, 0, null);
        addEntry(user17, sport6, Entry.EntryState.REGISTERED, 0, null);
    }
}
