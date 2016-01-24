package cz.muni.fi.PA165.facade;

import cz.muni.fi.PA165.dto.CreateUserDTO;
import cz.muni.fi.PA165.dto.UserAuthenticateDTO;
import cz.muni.fi.PA165.dto.UserDTO;
import cz.muni.fi.PA165.dto.facade.UserFacade;
import cz.muni.fi.PA165.entity.Usr;
import cz.muni.fi.PA165.service.BeanMappingService;
import cz.muni.fi.PA165.service.UserService;
import cz.muni.fi.PA165.service.config.ServiceConfiguration;
import cz.muni.fi.PA165.service.facade.UserFacadeImpl;
import java.util.ArrayList;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import org.mockito.Matchers;
import static org.mockito.Matchers.eq;
import org.mockito.Mockito;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.Assert;

/**
 * Created by Jamik on 17.12.2015.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private UserService userService;

    @Mock
    private BeanMappingService beanMappingService;

    private UserFacade userFacade;
    
    private CreateUserDTO createUser1DTO;
    private UserAuthenticateDTO userAuthenticate1DTO;
    private UserAuthenticateDTO wrongUserAuthenticate1DTO;
    private UserDTO user1DTO;
    private Usr user1;
    private UserDTO user2DTO;
    private Usr user2;
    private List<UserDTO> userDTOList;
    private List<Usr> userList;

    @BeforeClass
    public void setupData()
    {
        MockitoAnnotations.initMocks(this);
        
        userFacade = new UserFacadeImpl(userService, beanMappingService);
        
        createUser1DTO = new CreateUserDTO();
        createUser1DTO.setId(11L);
        createUser1DTO.setEmail("mail@email.com");
        createUser1DTO.setPassword("heslo");
        createUser1DTO.setName("name");
        createUser1DTO.setSurname("surname");
        
        userAuthenticate1DTO = new UserAuthenticateDTO();
        userAuthenticate1DTO.setEmail(createUser1DTO.getEmail());
        userAuthenticate1DTO.setPassword(createUser1DTO.getPassword());
        
        wrongUserAuthenticate1DTO = new UserAuthenticateDTO();
        wrongUserAuthenticate1DTO.setEmail("bademail@nic.nic");
        wrongUserAuthenticate1DTO.setPassword("wrongpassword");
        
        user1DTO = new UserDTO();
        user1DTO.setId(createUser1DTO.getId());
        user1DTO.setEmail(createUser1DTO.getEmail());
        user1DTO.setName(createUser1DTO.getName());
        user1DTO.setSurname(createUser1DTO.getSurname());
        user1DTO.setAdmin(false);
        
        user1 = new Usr();
        user1.setId(user1DTO.getId());
        user1.setEmail(user1DTO.getEmail());
        user1.setName(user1DTO.getName());
        user1.setSurname(user1DTO.getSurname());
        user1.setAdmin(user1DTO.getAdmin());
        
        user2DTO = new UserDTO();
        user2DTO.setId(22L);
        user2DTO.setEmail("dalsimail@nikde.nic");
        user2DTO.setName("Jmeno2");
        user2DTO.setSurname("Prijmeni2");
        user2DTO.setAdmin(false);
        
        user2 = new Usr();
        user2.setId(user2DTO.getId());
        user2.setEmail(user2DTO.getEmail());
        user2.setName(user2DTO.getName());
        user2.setSurname(user2DTO.getSurname());
        user2.setAdmin(user2DTO.getAdmin());
        
        userDTOList = new ArrayList<>();
        userDTOList.add(user1DTO);
        userDTOList.add(user2DTO);
        
        userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
    }

    @Test
    public void registerUserTest(){
        long returnId = 55L;
        
        doAnswer(
            new Answer() {
                @Override
                public Object answer(InvocationOnMock invocation) {
                    Object[] args = invocation.getArguments();
                    ((Usr) args[0]).setId(returnId);
                    return null;
                }
            }
        ).when(userService).registerSportsman(Matchers.anyObject(), eq(createUser1DTO.getPassword()));
        
        Assert.assertEquals(userFacade.registerUser(createUser1DTO), returnId);
    }

    @Test
    public void updateUserTest(){
        when(userService.findById(user1.getId())).thenReturn(user1);
        
        userFacade.updateUser(user1DTO);
        
        verify(userService).updateSportsman(user1);
    }
    
    @Test
    public void findUserByIdTest(){
        when(beanMappingService.mapTo(user1, UserDTO.class)).thenReturn(user1DTO);
        
        when(userService.findById(user1.getId())).thenReturn(user1);
        Assert.assertEquals(userFacade.findUserById(user1.getId()), user1DTO);
        
        when(userService.findById(0L)).thenReturn(null);
        Assert.assertEquals(userFacade.findUserById(0L), null);
    }
    
    @Test
    public void findUserByEmailTest(){
        when(beanMappingService.mapTo(user1, UserDTO.class)).thenReturn(user1DTO);
        
        when(userService.findUserByEmail(user1.getEmail())).thenReturn(user1);
        Assert.assertEquals(userFacade.findUserByEmail(user1.getEmail()), user1DTO);
        
        when(userService.findUserByEmail("")).thenReturn(null);
        Assert.assertEquals(userFacade.findUserByEmail(""), null);
    }
    
    @Test
    public void getAllUsersTest(){
        when(beanMappingService.mapTo(userList, UserDTO.class)).thenReturn(userDTOList);
        when(userService.getAllSportsmans()).thenReturn(userList);
        
        Assert.assertEquals(userFacade.getAllUsers(), userDTOList);
    }
    
    @Test
    public void authenticateTest(){
        when(userService.findUserByEmail(userAuthenticate1DTO.getEmail())).thenReturn(user1);
        when(userService.authenticate(user1, userAuthenticate1DTO.getPassword())).thenReturn(true);
        Assert.assertEquals(userFacade.authenticate(userAuthenticate1DTO), true);
        
        when(userService.findUserByEmail(wrongUserAuthenticate1DTO.getEmail())).thenReturn(null);
        when(userService.authenticate(null, wrongUserAuthenticate1DTO.getPassword())).thenReturn(false);
        Assert.assertEquals(userFacade.authenticate(wrongUserAuthenticate1DTO), false);
    }

}
