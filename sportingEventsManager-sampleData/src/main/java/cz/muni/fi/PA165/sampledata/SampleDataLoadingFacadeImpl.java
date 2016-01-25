package cz.muni.fi.PA165.sampledata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cz.muni.fi.PA165.dao.EntryDao;
import cz.muni.fi.PA165.dao.EventDao;
import cz.muni.fi.PA165.dao.SportDao;
import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Event;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Usr;
import cz.muni.fi.PA165.service.UserService;

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

	private List<Usr> users = new ArrayList<>();
	private List<Event> events = new ArrayList<>();
	private List<Sport> sports = new ArrayList<>();

	@Override
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

	private Date nowPlusMinutes(int minutes) {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.MINUTE, minutes);

		return c.getTime();
	}


	private Date resultTime(int minutes, int seconds, int miliseconds) {
		Date time = new Date(0, 0, 0, 0, minutes, seconds);
		time.setTime(time.getTime() + miliseconds);
		return time;
	}

	private void createSomeUsers() {

		users.add(addUser("password", "Adminator", "von Root", "admin@email.com", true)); // admin

		users.add(addUser("password", "Usain", "Thunder", "user4@email.com", false)); // bezec
		users.add(addUser("password", "Emil", "Floodman", "user3@email.com", false)); // bezec
		users.add(addUser("password", "Paula", "Radcliffe", "user2@email.com", false)); // bezec
		users.add(addUser("password", "Roger", "Canister", "user5@email.com", false)); // bezec
		users.add(addUser("password", "Jesse", "Owens", "user6@email.com", false)); // bezec
		users.add(addUser("password", "Kathrine", "Switzer", "user7@email.com", false)); // bezec

		users.add(addUser("password", "Missy", "Franklin", "user8@email.com", false)); // plavec
		users.add(addUser("password", "David", "Nolan", "user9@email.com", false)); // plavec
		users.add(addUser("password", "Grant", "Hackett", "user10@email.com", false)); // plavec
		users.add(addUser("password", "Michael", "Phelps", "user11@email.com", false)); // plavec
		users.add(addUser("password", "Krisztina", "Egerszegi", "user12@email.com", false)); // plavec

		users.add(addUser("password", "Eddy", "Merckx", "user13@email.com", false)); // cyklista
		users.add(addUser("password", "Jan", "Ullrich", "user14@email.com", false)); // cyklista
		users.add(addUser("password", "Fausto", "Coppi", "user15@email.com", false)); // cyklista
		users.add(addUser("password", "Miguel", "Indurain", "user16@email.com", false)); // cyklista
		users.add(addUser("password", "Sean", "Kelly", "user17@email.com", false)); // cyklista
	}

	private void createSomeEvents() {
		events.add(addEvent("Running Event",
				"Add some description. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
				nowPlusMinutes(-30), nowPlusMinutes(45)));

		events.add(addEvent("Simultaneous Triatlon Event",
				"Add some description. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
				nowPlusMinutes(0), nowPlusMinutes(50)));
	}

	private void createSomeSports() {
		sports.add(addSport("Run - 100m", nowPlusMinutes(-25), nowPlusMinutes(-15), events.get(0)));
		sports.add(addSport("Run - 800m", nowPlusMinutes(-5), nowPlusMinutes(10), events.get(0)));
		sports.add(addSport("Run - 2000m", nowPlusMinutes(20), nowPlusMinutes(40), events.get(0)));

		sports.add(addSport("Run - 1,5km", nowPlusMinutes(5), nowPlusMinutes(20), events.get(1)));
		sports.add(addSport("Swimming - 750m", nowPlusMinutes(10), nowPlusMinutes(30), events.get(1)));
		sports.add(addSport("Cycling - 20km", nowPlusMinutes(15), nowPlusMinutes(45), events.get(1)));
	}

	private void createSomeEntries() {

		//Event 1
		addEntry(users.get(1), sports.get(0), Entry.EntryState.FINISHED, 1, resultTime(0, 9, 270));
		addEntry(users.get(2), sports.get(0), Entry.EntryState.FINISHED, 2, resultTime(0, 9, 634));
		addEntry(users.get(3), sports.get(0), Entry.EntryState.FINISHED, 3, resultTime(0, 10, 512));
		addEntry(users.get(4), sports.get(0), Entry.EntryState.FINISHED, 4, resultTime(0, 10, 900));
		addEntry(users.get(5), sports.get(0), Entry.EntryState.FINISHED, 5, resultTime(0, 11, 505));
		addEntry(users.get(6), sports.get(0), Entry.EntryState.FINISHED, 6, resultTime(0, 12, 333));

		addEntry(users.get(2), sports.get(1), Entry.EntryState.FINISHED, 1, resultTime(1, 02, 333));
		addEntry(users.get(3), sports.get(1), Entry.EntryState.FINISHED, 2, resultTime(1, 04, 834));
		addEntry(users.get(4), sports.get(1), Entry.EntryState.REGISTERED, 0, null);
		addEntry(users.get(5), sports.get(1), Entry.EntryState.REGISTERED, 0, null);
		addEntry(users.get(6), sports.get(1), Entry.EntryState.REGISTERED, 0, null);
		addEntry(users.get(7), sports.get(1), Entry.EntryState.DISQUALIFIED, 0, null);

		addEntry(users.get(2), sports.get(2), Entry.EntryState.REGISTERED, 0, null);
		addEntry(users.get(3), sports.get(2), Entry.EntryState.REGISTERED, 0, null);
		addEntry(users.get(4), sports.get(2), Entry.EntryState.REGISTERED, 0, null);

		//Event 2
		addEntry(users.get(5), sports.get(3), Entry.EntryState.REGISTERED, 0, null);
		addEntry(users.get(6), sports.get(3), Entry.EntryState.REGISTERED, 0, null);
		addEntry(users.get(4), sports.get(3), Entry.EntryState.REGISTERED, 0, null);

		addEntry(users.get(8), sports.get(4), Entry.EntryState.REGISTERED, 0, null);
		addEntry(users.get(9), sports.get(4), Entry.EntryState.REGISTERED, 0, null);
		addEntry(users.get(10), sports.get(4), Entry.EntryState.REGISTERED, 0, null);
		addEntry(users.get(7), sports.get(4), Entry.EntryState.REGISTERED, 0, null);
		addEntry(users.get(11), sports.get(4), Entry.EntryState.REGISTERED, 0, null);

		addEntry(users.get(12), sports.get(5), Entry.EntryState.REGISTERED, 0, null);
		addEntry(users.get(14), sports.get(5), Entry.EntryState.REGISTERED, 0, null);
		addEntry(users.get(15), sports.get(5), Entry.EntryState.REGISTERED, 0, null);
		addEntry(users.get(16), sports.get(5), Entry.EntryState.REGISTERED, 0, null);
		addEntry(users.get(13), sports.get(5), Entry.EntryState.REGISTERED, 0, null);
	}
}
