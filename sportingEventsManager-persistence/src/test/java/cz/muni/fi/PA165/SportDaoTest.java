package cz.muni.fi.PA165;

import cz.muni.fi.PA165.dao.EventDao;
import cz.muni.fi.PA165.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import java.util.Date;
import java.util.List;

import cz.muni.fi.PA165.dao.SportDao;
import cz.muni.fi.PA165.entity.Sport;

/**
 * @author jbouska
 */

@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SportDaoTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SportDao sportDao;

	@Autowired
	private EventDao eventDao;

	@PersistenceContext
	private EntityManager em;

	private Event event;

	@BeforeMethod
	public void prepareEvent() {
		event = new Event();
		event.setName("Event 1");
		event.setDescription("Event description.");
		event.setStartTime(new Date());
		event.setEndTime(new Date());
		eventDao.create(event);
	}

	@Test
	public void shouldfindAll() {
		Sport sp1 = new Sport();
		Sport sp2 = new Sport();

		sp1.setName("tennis");
		sp2.setName("hockey");

		sp1.setEvent(event);
		sp2.setEvent(event);

		sportDao.create(sp1);
		sportDao.create(sp2);
		List<Sport> sports = sportDao.findAll();

		Assert.assertEquals(sports.size(), 2);
		Assert.assertTrue(sports.contains(sp1));
		Assert.assertTrue(sports.contains(sp2));

	}

	@Test
	public void shouldFindByID() {
		Sport sp = new Sport();
		sp.setName("tennis");
		sp.setEvent(event);

		sportDao.create(sp);

		Assert.assertEquals(sportDao.findById(sp.getIdSport()), sp);
	}

	@Test(expectedExceptions = ConstraintViolationException.class)
	public void importWithoutName() {
		Sport sp = new Sport();
		sportDao.create(sp);
	}

	@Test(expectedExceptions = PersistenceException.class)
	public void nameShouldBeUnique() {
		Sport sp1 = new Sport();
		Sport sp2 = new Sport();

		sp1.setName("tennis");
		sp2.setName("tennis");

		sp1.setEvent(event);
		sp2.setEvent(event);

		sportDao.create(sp1);
		sportDao.create(sp2);
	}

	@Test
	public void shouldFindByName() {
		Sport sp = new Sport();
		sp.setName("tennis");
		sp.setEvent(event);

		sportDao.create(sp);

		Assert.assertEquals(sportDao.findByName(sp.getName()), sp);
	}

	@Test
	public void shouldDelete() {

		Sport sp1 = new Sport();
		Sport sp2 = new Sport();
		sp1.setName("tennis");
		sp2.setName("hockey");
		sp1.setEvent(event);
		sp2.setEvent(event);

		sportDao.create(sp1);
		sportDao.create(sp2);

		sportDao.delete(sp1);


		List<Sport> sports = sportDao.findAll();

		Assert.assertEquals(sports.size(), 1);
		Assert.assertTrue(sports.contains(sp2));

	}
}
