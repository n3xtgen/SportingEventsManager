package cz.muni.fi.PA165;

import cz.muni.fi.PA165.dao.SportsmanDao;
import cz.muni.fi.PA165.entity.Sportsman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author Vladimir
 */
@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SportsmanDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SportsmanDao sportsmanDao;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void shouldfindAll(){
        Sportsman first = new Sportsman();
        Sportsman second = new Sportsman();

        first.setName("Usain");
        first.setSurname("Bolt");
        first.setCitizenIdNumber("900522/7654");
        first.setEmail("email@email.cz");
        second.setName("Miroslav");
        second.setSurname("Satan");
        second.setCitizenIdNumber("800322/7654");
        second.setEmail("email2@email.cz");

        sportsmanDao.create(first);
        sportsmanDao.create(second);
        List<Sportsman> sportsmans = sportsmanDao.findAll();

        Assert.assertEquals(sportsmans.size(), 2);
        Assert.assertTrue(sportsmans.contains(first));
        Assert.assertTrue(sportsmans.contains(second));
    }

    @Test
    public void shouldFindByID() {
        Sportsman first = new Sportsman();
        first.setName("Usain");
        first.setSurname("Bolt");
        first.setCitizenIdNumber("900522/7654");
        first.setEmail("email@email.cz");
        sportsmanDao.create(first);

        Assert.assertEquals(sportsmanDao.findById(first.getIdSportsman()), first);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void importWithoutPersonId() {
        Sportsman first = new Sportsman();
        first.setName("Jan");
        first.setSurname("Zeleny");
        first.setEmail("email@email.cz");
        sportsmanDao.create(first);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void importWithoutName() {
        Sportsman first = new Sportsman();
        first.setSurname("Novák");
        first.setCitizenIdNumber("900522/7652");
        first.setEmail("email@email.cz");
        sportsmanDao.create(first);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void importWithoutSurname() {
        Sportsman first = new Sportsman();
        first.setName("Jan");
        first.setCitizenIdNumber("900522/7652");
        first.setEmail("email@email.cz");
        sportsmanDao.create(first);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void personIdShouldBeUnique() {
        Sportsman first = new Sportsman();
        Sportsman second = new Sportsman();

        first.setName("Dana");
        first.setSurname("Obycajna");
        first.setCitizenIdNumber("900522/7659");
        first.setEmail("email@email.cz");
        second.setName("Klara");
        second.setSurname("Novakova");
        second.setCitizenIdNumber("900522/7659");
        second.setEmail("email2@email.cz");
        

        sportsmanDao.create(first);
        sportsmanDao.create(second);
    }

    @Test
    public void shouldFindByPersonId() {
        Sportsman first = new Sportsman();
        first.setName("Anka");
        first.setSurname("Obycajna");
        first.setCitizenIdNumber("900522/7654");
        first.setEmail("email@email.cz");
        sportsmanDao.create(first);

        Assert.assertEquals(sportsmanDao.findByCitizenIdNumber(first.getCitizenIdNumber()), first);
    }

    @Test
    public void shouldDelete() {

        Sportsman first = new Sportsman();
        Sportsman second = new Sportsman();
        first.setName("Anka");
        first.setCitizenIdNumber("900522/7654");
        first.setSurname("Obycajna");
        first.setEmail("email@email.cz");
        second.setName("Patka");
        second.setCitizenIdNumber("900522/7622");
        second.setSurname("Novakova");
         second.setEmail("email2@email.cz");


        sportsmanDao.create(first);
        sportsmanDao.create(second);

        sportsmanDao.delete(first);


        List<Sportsman> sportsmans = sportsmanDao.findAll();

        Assert.assertEquals(sportsmans.size(), 1);
        Assert.assertTrue(sportsmans.contains(second));

    }

}
