package cz.muni.fi.PA165;

import cz.muni.fi.PA165.dao.PromoterDao;
import cz.muni.fi.PA165.entity.Promoter;
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
 * @author N3xtGeN
 */
@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PromoterDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private PromoterDao promoterDao;

    @PersistenceContext
    private EntityManager em;


    @Test
    public void shouldfindAll(){
        Promoter first = new Promoter();
        Promoter second = new Promoter();

        first.setName("Jan");
        first.setSurname("Novák");
        first.setCitizenIdNumber("900522/7654");
        second.setName("David");
        second.setSurname("Starý");
        second.setCitizenIdNumber("800322/7654");

        promoterDao.create(first);
        promoterDao.create(second);
        List<Promoter> promoters = promoterDao.findAll();

        Assert.assertEquals(promoters.size(), 2);
        Assert.assertTrue(promoters.contains(first));
        Assert.assertTrue(promoters.contains(second));
    }

    @Test
    public void shouldFindByID() {
        Promoter first = new Promoter();
        first.setName("Jan");
        first.setSurname("Novák");
        first.setCitizenIdNumber("900522/7654");

        promoterDao.create(first);

        Assert.assertEquals(promoterDao.findById(first.getIdPromoter()), first);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void importWithoutName() {
        Promoter first = new Promoter();
        first.setSurname("Novák");
        first.setCitizenIdNumber("900522/7652");
        promoterDao.create(first);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void importWithoutSurname() {
        Promoter first = new Promoter();
        first.setName("Jan");
        first.setCitizenIdNumber("900522/7652");
        promoterDao.create(first);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void importWithoutCitizenIdNumber() {
        Promoter first = new Promoter();
        first.setName("Jan");
        first.setSurname("Novák");
        promoterDao.create(first);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void citizenIdNumberShouldBeUnique() {
        Promoter first = new Promoter();
        Promoter second = new Promoter();

        first.setName("Dana");
        first.setSurname("Obycajna");
        first.setCitizenIdNumber("900522/7659");
        second.setName("Klara");
        second.setSurname("Novakova");
        second.setCitizenIdNumber("900522/7659");

        promoterDao.create(first);
        promoterDao.create(second);
    }

    @Test
    public void shouldFindBySurname() {
        Promoter first = new Promoter();
        first.setName("Anka");
        first.setSurname("Obycajna");
        first.setCitizenIdNumber("900522/7654");

        promoterDao.create(first);

        Assert.assertEquals(promoterDao.findBySurname(first.getSurname()), first);
    }

    @Test
    public void shouldFindByCitizenIdNumber() {
        Promoter first = new Promoter();
        first.setName("Anka");
        first.setSurname("Obycajna");
        first.setCitizenIdNumber("900522/7654");

        promoterDao.create(first);

        Assert.assertEquals(promoterDao.findByCitizenIdNumber(first.getCitizenIdNumber()), first);
    }

    @Test
    public void shouldDelete() {
        Promoter first = new Promoter();
        Promoter second = new Promoter();
        first.setName("Anka");
        first.setCitizenIdNumber("900522/7654");
        first.setSurname("Obycajna");
        second.setName("Patka");
        second.setCitizenIdNumber("900522/7622");
        second.setSurname("Novakova");

        promoterDao.create(first);
        promoterDao.create(second);

        promoterDao.delete(first);

        List<Promoter> promoters = promoterDao.findAll();

        Assert.assertEquals(promoters.size(), 1);
        Assert.assertTrue(promoters.contains(second));
    }
}
