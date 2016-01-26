package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.dao.SportDao;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.service.SportService;
import cz.muni.fi.PA165.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.xml.ws.Service;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by Vladimir on 25.11.2015.
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class SportServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private SportDao sportDao;

    @Autowired
    @InjectMocks
    private SportService sportService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Sport sport1;

    private Sport sport2;

    @BeforeMethod
    public void prepareTestData() {
        sport1 = new Sport();
        sport1.setName("Squash");

        sport2 = new Sport();
        sport2.setName("Basketball");
    }

    @Test
    public void ShouldFindByName(){
        String name = "Squash";
        when(sportDao.findByName(name)).thenReturn(sport1);

        Assert.assertEquals(sportService.findSportByName(name), sport1);
    }

    @Test
    public void ShouldFindById(){
        long id = 2;
        when(sportDao.findById(id)).thenReturn(sport2);

        Assert.assertEquals(sportService.findSportById(id), sport2);
    }

    @Test
    public void shouldGetAllSports()
    {
        List<Sport> sports = new ArrayList<>();
        sports.add(sport1);
        sports.add(sport2);

        when(sportDao.findAll()).thenReturn(sports);

        Assert.assertEquals(sportService.getAllSports(),sports);
    }
}
