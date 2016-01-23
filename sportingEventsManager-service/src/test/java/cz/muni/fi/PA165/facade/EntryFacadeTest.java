package cz.muni.fi.PA165.facade;

import cz.muni.fi.PA165.dto.*;
import cz.muni.fi.PA165.dto.facade.EntryFacade;
import cz.muni.fi.PA165.dto.facade.EventFacade;
import cz.muni.fi.PA165.dto.facade.SportFacade;
import cz.muni.fi.PA165.dto.facade.UserFacade;
import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Usr;
import cz.muni.fi.PA165.service.BeanMappingService;
import cz.muni.fi.PA165.service.EntryService;
import cz.muni.fi.PA165.service.SportService;
import cz.muni.fi.PA165.service.UserService;
import cz.muni.fi.PA165.service.config.ServiceConfiguration;
import cz.muni.fi.PA165.service.facade.EntryFacadeImpl;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 * @author n3xtgen
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class EntryFacadeTest extends AbstractTestNGSpringContextTests  {

    @Mock
    private EntryService entryService;
    
    @Mock
    private SportService sportService;
    
    @Mock
    private UserService userService;
    
    @Mock
    private BeanMappingService beanMappingService;

    private EntryFacade entryFacade;
    
    private Sport sport1;
    private Sport sport2;
    private Usr sportsman1;
    private Usr sportsman2;
    
    private CreateEntryDTO createEntry1DTO;
    private EntryDTO entry1DTO;
    private Entry entry1;
    private EntryDTO entry2DTO;
    private Entry entry2;
    
    private List<Entry> entryCollection;
    private List<EntryDTO> entryDTOCollection;

    @BeforeClass
    public void setupData(){
        MockitoAnnotations.initMocks(this);
        
        entryFacade = new EntryFacadeImpl(sportService, userService, entryService, beanMappingService);
        
        sport1 = new Sport();
        sport1.setIdSport(11L);
        sport1.setName("Sport1");
        
        sport2 = new Sport();
        sport2.setIdSport(12L);
        sport2.setName("Sport2");
        
        sportsman1 = new Usr();
        sportsman1.setId(21L);
        sportsman1.setEmail("user1@mail.com");
        
        sportsman2 = new Usr();
        sportsman2.setId(22L);
        sportsman2.setEmail("user2@mail.com");
        
        createEntry1DTO = new CreateEntryDTO();
        createEntry1DTO.setSportId(sport1.getIdSport());
        createEntry1DTO.setSportsmanId(sportsman1.getId());
        
        entry1DTO = new EntryDTO();
        entry1DTO.setIdEntry(91L);
        entry1DTO.setSport(new SportDTO());
        entry1DTO.setUsr(new UserDTO());

        entry1 = new Entry();
        entry1.setIdEntry(91L);
        entry1.setSport(sport1);
        entry1.setUsr(sportsman1);
        
        entry2DTO = new EntryDTO();
        entry2DTO.setIdEntry(92L);
        entry2DTO.setSport(new SportDTO());
        entry2DTO.setUsr(new UserDTO());

        entry2 = new Entry();
        entry2.setIdEntry(92L);
        entry2.setSport(sport2);
        entry2.setUsr(sportsman2);
        
        entryCollection = new ArrayList<>();
        entryCollection.add(entry1);
        entryCollection.add(entry2);
        
        entryDTOCollection = new ArrayList<>();
        entryDTOCollection.add(entry1DTO);
        entryDTOCollection.add(entry2DTO);
    }

    @Test
    public void registerTest() {
        when(sportService.findSportById(sport1.getIdSport())).thenReturn(sport1);
        when(userService.findById(sportsman1.getId())).thenReturn(sportsman1);
        when(entryService.createEntry(Matchers.anyObject())).thenReturn(1L);
        
        entryFacade.registerEntry(createEntry1DTO);
        
        verify(entryService).createEntry(Matchers.anyObject());
    }

    @Test
    public void updateTest(){
        when(beanMappingService.mapTo(entry1DTO, Entry.class)).thenReturn(entry1);
        
        entryFacade.updateEntry(entry1DTO);
        
        verify(entryService).updateEntry(entry1);
    }

    @Test
    public void deleteTest(){
        when(entryService.findEntryById(entry1.getIdEntry())).thenReturn(entry1);
        
        entryFacade.deleteEntry(entry1.getIdEntry());
        
        verify(entryService).deleteEntry(entry1);
    }

    @Test
    public void findEntryByIdTest(){
        when(entryService.findEntryById(entry1.getIdEntry())).thenReturn(entry1);
        when(beanMappingService.mapTo(entry1, EntryDTO.class)).thenReturn(entry1DTO);
        
        Assert.assertEquals(entryFacade.findEntryById(entry1.getIdEntry()), entry1DTO);
    }

    @Test
    public void findEntriesBySportIdTest() {
        when(sportService.findSportById(sport1.getIdSport())).thenReturn(sport1);
        when(entryService.findEntriesBySport(sport1)).thenReturn(entryCollection);
        when(beanMappingService.mapTo(entryCollection, EntryDTO.class)).thenReturn(entryDTOCollection);
        
        Assert.assertEquals(entryFacade.findEntriesBySportId(sport1.getIdSport()), entryDTOCollection);
    }

    @Test
    public void findEntriesBySportsmanIdTest() {
        when(userService.findById(sportsman1.getId())).thenReturn(sportsman1);
        when(entryService.findEntriesBySportsman(sportsman1)).thenReturn(entryCollection);
        when(beanMappingService.mapTo(entryCollection, EntryDTO.class)).thenReturn(entryDTOCollection);
        
        Assert.assertEquals(entryFacade.findEntriesBySportsmanId(sportsman1.getId()), entryDTOCollection);
    }
    
    @Test
    public void findEntryBySportsmanAndSportIdTest() {
        when(sportService.findSportById(sport1.getIdSport())).thenReturn(sport1);
        when(userService.findById(sportsman1.getId())).thenReturn(sportsman1);
        when(entryService.findEntryBySportAndSportsman(sport1, sportsman1)).thenReturn(entry1);
        when(beanMappingService.mapTo(entry1, EntryDTO.class)).thenReturn(entry1DTO);
        
        Assert.assertEquals(entryFacade.findEntryBySportsmanAndSportId(sport1.getIdSport(), sportsman1.getId()), entry1DTO);
    }
}
