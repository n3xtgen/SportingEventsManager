package cz.muni.fi.PA165.facade;

import cz.muni.fi.PA165.dto.CreateUserDTO;
import cz.muni.fi.PA165.dto.UserDTO;
import cz.muni.fi.PA165.dto.facade.UserFacade;
import cz.muni.fi.PA165.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Jamik on 17.12.2015.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    UserFacade userFacade;

    private CreateUserDTO someUser;
    private CreateUserDTO someUser2;

    @BeforeClass
    public void setupData(){
        someUser = new CreateUserDTO();
        someUser.setName("Petr");
        someUser.setSurname("Filek");
        someUser.setPassword("askldjlsdfjk");
        someUser.setEmail("Petr@filek.cz");
        userFacade.registerUser(someUser);

        someUser2 = new CreateUserDTO();
        someUser2.setName("Tomas");
        someUser2.setSurname("Lilek");
        someUser2.setPassword("asdasdasdfjk");
        someUser2.setEmail("Tomas@Lilek.cz");
        userFacade.registerUser(someUser2);

    }

    @Test
    public void shouldRegister(){
        Assert.assertEquals(userFacade.findUserById(someUser.getId()).getId(), someUser.getId());
    }

    @Test
    public void shouldFindById(){
        Assert.assertEquals(userFacade.findUserById(someUser.getId()).getId(), someUser.getId());
    }

    @Test
    public void shouldFindByEmail(){
        UserDTO u = userFacade.findUserById(someUser.getId());
        Assert.assertEquals(userFacade.findUserByEmail(someUser.getEmail()), u);
    }

    @Test
    public void shouldUpdate(){
        UserDTO u = userFacade.findUserById(someUser.getId());
        String sNameOrig = u.getSurname();
        u.setSurname("NewSurname");
        userFacade.updateUser(u);

        Assert.assertNotEquals(sNameOrig, userFacade.findUserById(someUser.getId()));
    }


    @Test
    public void shouldGetAll(){
        Assert.assertEquals(userFacade.getAllUsers().size(), 2);
    }

    @Test
    public void shouldAuthenticate(){

    }
}


