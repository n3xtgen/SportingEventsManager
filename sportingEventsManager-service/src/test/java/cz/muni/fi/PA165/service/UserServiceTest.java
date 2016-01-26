/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.Exceptions.DataAccessException;
import cz.muni.fi.PA165.dao.UserDao;
import cz.muni.fi.PA165.entity.Usr;
import cz.muni.fi.PA165.service.UserService;
import cz.muni.fi.PA165.service.config.ServiceConfiguration;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author jbouska
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private UserDao userDao;

    @Autowired
    @InjectMocks
    private UserService userService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Usr s;
    
    private Usr s1;

    @BeforeMethod
    public void prepareTestData() {
        s = new Usr();
        s.setName("Petr");
        s.setName("Filek");
        s.setEmail("Petr@filek.cz");
        
        s1 = new Usr();
        s1.setName("Petr1");
        s1.setName("Filek1");
        s1.setEmail("Petr@filek1.cz");

    }


@Test
    public void shouldSetSportsmanPasseordHash()
    {
        userService.registerSportsman(s, "password");
       Assert.assertFalse(s.getPasswordHash().isEmpty());
       Assert.assertNotEquals(s.getPasswordHash(), "password");
    }
    
    @Test
    public void shouldGetAllSportsmans()
    {
        List<Usr> spList = new ArrayList<>();
        spList.add(s);
        spList.add(s1);
        
        when(userDao.findAll()).thenReturn(spList);
        
       Assert.assertEquals(userService.getAllSportsmans(),spList);
    }
    
    @Test
    public void shouldAuthenticate()
    {
        //should set sportsmans password hash
        userService.registerSportsman(s, "password");
       
        Assert.assertTrue(userService.authenticate(s,"password"));
        Assert.assertFalse(userService.authenticate(s,"fake"));
        
    }
    
    @Test
    public void shouldFindByID()
    {
        long id = 1;
        when(userDao.findById(id)).thenReturn(s);
        
        Assert.assertEquals(userService.findById(id),s);
    }
    
     @Test
    public void shouldFindByEmail()
    {
       String email = "Petr@filek.cz";
        when(userDao.findByEmail(email)).thenReturn(s);
        
        Assert.assertEquals(userService.findUserByEmail(email),s);
    }
    
      @Test
    public void shouldFindBySurname()
    {
       String surname = "Filek";
       List<Usr> spList = new ArrayList<>();
        spList.add(s);
        when(userDao.findBySurname(surname)).thenReturn(spList);
        
        Assert.assertEquals(userService.findUserBySurname(surname),spList);
    }
    
       @Test(expectedExceptions = DataAccessException.class)
    public void shouldThrowExceptoin()
    {
       when(userDao.findAll()).thenThrow(new PersistenceException());
      
       userService.getAllSportsmans();
    }
    
    
}
