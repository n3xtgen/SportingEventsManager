package cz.muni.fi.PA165.facade;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cz.muni.fi.PA165.dao.UserDao;
import cz.muni.fi.PA165.dto.CreateUserDTO;
import cz.muni.fi.PA165.dto.UserDTO;
import cz.muni.fi.PA165.dto.facade.UserFacade;
import cz.muni.fi.PA165.entity.Usr;
import cz.muni.fi.PA165.service.BeanMappingService;
import cz.muni.fi.PA165.service.UserService;
import cz.muni.fi.PA165.service.config.ServiceConfiguration;

import org.aspectj.lang.annotation.Before;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jamik on 17.12.2015.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private UserDao userDao;

    @InjectMocks
    @Autowired
    private UserService service;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private BeanMappingService mappingService;

    private UserDTO someUser;
    private Usr user;


    @BeforeClass
    public void inti()
    {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setupData(){

       user = new Usr();
        user.setEmail("mail@email.com");
        user.setName("name");
        user.setSurname("surname");
        user.setId((long)1);
        user.setAdmin(false);

        someUser = mappingService.mapTo(user, UserDTO.class);
    }


    @Test
    public void shouldFindByEmail(){
       when(userDao.findByEmail(user.getEmail())).thenReturn(user);
       Assert.assertEquals(userFacade.findUserByEmail(someUser.getEmail()).getEmail(), someUser.getEmail());
    }


    @Test
    public void shouldFindByID(){
        when(userDao.findById(someUser.getId())).thenReturn(user);

        Assert.assertEquals(userFacade.findUserByEmail(someUser.getEmail()).getId(), user.getId());
    }

    @Test void getAll()
    {
        List<Usr> users = new ArrayList<>();
        users.add(user);
        when(userDao.findAll()).thenReturn(users);

        Assert.assertEquals(userFacade.getAllUsers().size(), 1);
    }




}


