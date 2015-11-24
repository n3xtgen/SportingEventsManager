/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165;

import cz.muni.fi.PA165.Exceptions.DataAccessException;
import cz.muni.fi.PA165.dao.SportsmanDao;
import cz.muni.fi.PA165.entity.Sportsman;
import cz.muni.fi.PA165.service.SportsmanService;
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
import org.springframework.test.util.AssertionErrors;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author jbouska
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class SportsmanServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private SportsmanDao sportsmanDao;

    @Autowired
    @InjectMocks
    private SportsmanService sportsmanService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Sportsman s;
    
    private Sportsman s1;

    @BeforeMethod
    public void prepareTestData() {
        s = new Sportsman();
        s.setName("Petr");
        s.setName("Filek");
        s.setEmail("Petr@filek.cz");
        
        s1 = new Sportsman();
        s1.setName("Petr1");
        s1.setName("Filek1");
        s1.setEmail("Petr@filek1.cz");

    }


@Test
    public void shouldSetSportsmanPasseordHash()
    {
        sportsmanService.registerSportsman(s, "password");
       Assert.assertFalse(s.getPasswordHash().isEmpty());
       Assert.assertNotEquals(s.getPasswordHash(), "password");
    }
    
    @Test
    public void shouldGetAllSportsmans()
    {
        List<Sportsman> spList = new ArrayList<>();
        spList.add(s);
        spList.add(s1);
        
        when(sportsmanDao.findAll()).thenReturn(spList);
        
       Assert.assertEquals(sportsmanService.getAllSportsmans(),spList);
    }
    
    @Test
    public void shouldAuthenticate()
    {
        //should set sportsmans password hash
        sportsmanService.registerSportsman(s, "password");
       
        Assert.assertTrue(sportsmanService.authenticate(s,"password"));
        Assert.assertFalse(sportsmanService.authenticate(s,"fake"));
        
    }
    
    @Test
    public void shouldFindByID()
    {
        long id = 1;
        when(sportsmanDao.findById(id)).thenReturn(s);
        
        Assert.assertEquals(sportsmanService.findSportsmanById(id),s);
    }
    
     @Test
    public void shouldFindByEmail()
    {
       String email = "Petr@filek.cz";
        when(sportsmanDao.findByEmail(email)).thenReturn(s);
        
        Assert.assertEquals(sportsmanService.findSportsmanByEmail(email),s);
    }
    
      @Test
    public void shouldFindBySurname()
    {
       String surname = "Filek";
       List<Sportsman> spList = new ArrayList<>();
        spList.add(s);
        when(sportsmanDao.findBySurname(surname)).thenReturn(spList);
        
        Assert.assertEquals(sportsmanService.findSportsmanBySurname(surname),spList);
    }
    
       @Test(expectedExceptions = DataAccessException.class)
    public void shouldThrowExceptoin()
    {
       when(sportsmanDao.findAll()).thenThrow(new PersistenceException());
      
       sportsmanService.getAllSportsmans();
    }
    
    
}
