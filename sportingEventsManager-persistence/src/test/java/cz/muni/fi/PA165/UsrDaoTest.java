package cz.muni.fi.PA165;

import cz.muni.fi.PA165.dao.UserDao;
import cz.muni.fi.PA165.entity.Usr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Vladimir
 */
@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UsrDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserDao userDao;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void shouldfindAll(){
        Usr first = new Usr();
        Usr second = new Usr();

        first.setName("Usain");
        first.setSurname("Bolt");
     
        first.setEmail("email@email.cz");
        second.setName("Miroslav");
        second.setSurname("Satan");

        second.setEmail("email2@email.cz");

        userDao.create(first);
        userDao.create(second);
     /*   List<Usr> users = userDao.findAll();

        Assert.assertEquals(users.size(), 2);
        Assert.assertTrue(users.contains(first));
        Assert.assertTrue(users.contains(second));*/
    }

 /*   @Test
    public void shouldFindByID() {
        Usr first = new Usr();
        first.setName("Usain");
        first.setSurname("Bolt");
  
        first.setEmail("email@email.cz");
        userDao.create(first);

        Assert.assertEquals(userDao.findById(first.getId()), first);
    }

  

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void importWithoutName() {
        Usr first = new Usr();
        first.setSurname("Novák");
     
        first.setEmail("email@email.cz");
        userDao.create(first);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void importWithoutSurname() {
        Usr first = new Usr();
        first.setName("Jan");
  
        first.setEmail("email@email.cz");
        userDao.create(first);
    }

    
   

    @Test
    public void shouldDelete() {

        Usr first = new Usr();
        Usr second = new Usr();
        first.setName("Anka");
        first.setSurname("Obycajna");
        first.setEmail("email@email.cz");
        second.setName("Patka");
        
        second.setSurname("Novakova");
         second.setEmail("email2@email.cz");


        userDao.create(first);
        userDao.create(second);

        userDao.delete(first);


        List<Usr> users = userDao.findAll();

        Assert.assertEquals(users.size(), 1);
        Assert.assertTrue(users.contains(second));

    }
    
     @Test
    public void shouldUpdate() {

        Usr first = new Usr();
        first.setName("Anka");
     
        first.setSurname("Obycajna");
        first.setEmail("email@email.cz");
       

        userDao.create(first);
        
        first.setName("Petra");
        userDao.update(first);
        
        Usr fromDB = userDao.findById(first.getId());
        
        Assert.assertEquals(fromDB.getName(), "Petra");
     

    }*/

}
