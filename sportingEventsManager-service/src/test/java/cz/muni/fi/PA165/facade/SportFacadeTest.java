package cz.muni.fi.PA165.facade;

import cz.muni.fi.PA165.dto.CreateSportDTO;
import cz.muni.fi.PA165.dto.EventDTO;
import cz.muni.fi.PA165.dto.SportDTO;
import cz.muni.fi.PA165.dto.facade.SportFacade;
import cz.muni.fi.PA165.entity.Event;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.service.BeanMappingService;
import cz.muni.fi.PA165.service.EntryService;
import cz.muni.fi.PA165.service.EventService;
import cz.muni.fi.PA165.service.SportService;
import cz.muni.fi.PA165.service.config.ServiceConfiguration;
import cz.muni.fi.PA165.service.facade.SportFacadeImpl;
import java.util.ArrayList;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import org.mockito.Matchers;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 * Created by Jamik on 17.12.2015.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class SportFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private EntryService entryService;
    
    @Mock
    private SportService sportService;
    
    @Mock
    private EventService eventService;
    
    @Mock
    private BeanMappingService beanMappingService;

    private SportFacade sportFacade;
    
    private Event event1;
    private EventDTO event1DTO;
    private CreateSportDTO createSport1DTO;
    private SportDTO sport1DTO;
    private Sport sport1;
    private SportDTO sport2DTO;
    private Sport sport2;
    private List<SportDTO> sportDTOList;
    private List<Sport> sportList;
    

    @BeforeClass
    public void setupData(){
        MockitoAnnotations.initMocks(this);
        
        sportFacade = new SportFacadeImpl(sportService, eventService, entryService, beanMappingService);
        
        Date eventStart = new Date();
        
        Date sportStart = new Date();
        sportStart.setTime(sportStart.getTime()+10000);
        Date sportEnd = new Date();
        sportEnd.setTime(sportEnd.getTime()+20000);
        
        Date eventEnd = new Date();
        eventEnd.setTime(eventEnd.getTime()+30000);
        
        event1 = new Event();
        event1.setIdEvent(11L);
        event1.setName("Sportovni den");
        event1.setDescription("Sportovni den na pocest nekoho");
        event1.setStartTime(eventStart);
        event1.setEndTime(eventEnd);
        
        event1DTO = new EventDTO();
        event1DTO.setIdEvent(event1.getIdEvent());
        event1DTO.setName(event1.getName());
        event1DTO.setDescription(event1.getDescription());
        event1DTO.setStartTime(event1.getStartTime());
        event1DTO.setEndTime(event1.getEndTime());
        
        createSport1DTO = new CreateSportDTO();
        createSport1DTO.setName("Hockey");
        createSport1DTO.setStartTime(sportStart);
        createSport1DTO.setEndTime(sportEnd);
        createSport1DTO.setEvent(event1DTO);

        sport1DTO = new SportDTO();
        sport1DTO.setIdSport(23L);
        sport1DTO.setName(createSport1DTO.getName());
        sport1DTO.setStartTime(createSport1DTO.getStartTime());
        sport1DTO.setEndTime(createSport1DTO.getEndTime());
        sport1DTO.setEvent(event1DTO);
        
        sport1 = new Sport();
        sport1.setIdSport(sport1DTO.getIdSport());
        sport1.setName(sport1DTO.getName());
        sport1.setStartTime(sport1DTO.getStartTime());
        sport1.setEndTime(sport1DTO.getEndTime());
        sport1.setEvent(event1);
        
        sport2DTO = new SportDTO();
        sport2DTO.setIdSport(36L);
        sport2DTO.setName("Football");
        sport2DTO.setStartTime(sportStart);
        sport2DTO.setEndTime(sportEnd);
        sport2DTO.setEvent(event1DTO);
        
        sport2 = new Sport();
        sport2.setIdSport(sport2DTO.getIdSport());
        sport2.setName(sport2DTO.getName());
        sport2.setStartTime(sport2DTO.getStartTime());
        sport2.setEndTime(sport2DTO.getEndTime());
        sport2.setEvent(event1);
        
        sportDTOList = new ArrayList<>();
        sportDTOList.add(sport1DTO);
        sportDTOList.add(sport2DTO);
        
        sportList = new ArrayList<>();
        sportList.add(sport1);
        sportList.add(sport2);
    }

    @Test
    public void addNewSportTest() {
        when(eventService.findEventById(event1DTO.getIdEvent())).thenReturn(event1);
        when(sportService.addNewSport(Matchers.anyObject())).thenReturn(1L);
        
        sportFacade.addNewSport(createSport1DTO);
        
        verify(sportService).addNewSport(Matchers.anyObject());
    }

    @Test
    public void updateSportTest(){
        when(sportService.findSportById(sport1DTO.getIdSport())).thenReturn(sport1);
        
        sportFacade.updateSport(sport1DTO);
        
        verify(sportService).updateSport(sport1);
    }

    @Test
    public void findSportByIdTest(){
        when(beanMappingService.mapTo(sport1, SportDTO.class)).thenReturn(sport1DTO);
        
        when(sportService.findSportById(sport1.getIdSport())).thenReturn(sport1);
        Assert.assertEquals(sportFacade.findSportById(sport1.getIdSport()), sport1DTO);
        
        when(sportService.findSportById(0L)).thenReturn(null);
        Assert.assertEquals(sportFacade.findSportById(0L), null);
    }

    @Test
    public void findSportByNameTest(){
        when(beanMappingService.mapTo(sport1, SportDTO.class)).thenReturn(sport1DTO);
        
        when(sportService.findSportByName(sport1.getName())).thenReturn(sport1);
        Assert.assertEquals(sportFacade.findSportByName(sport1.getName()), sport1DTO);
        
        when(sportService.findSportByName("neexistujici")).thenReturn(null);
        Assert.assertEquals(sportFacade.findSportByName("neexistujici"), null);
    }

    @Test
    public void getAllSportsTest(){
        when(sportService.getAllSports()).thenReturn(sportList);
        when(beanMappingService.mapTo(sportList, SportDTO.class)).thenReturn(sportDTOList);
        
        Assert.assertEquals(sportFacade.getAllSports(), sportDTOList);
    }
    
    @Test
    public void updateSportResultsTest(){
        when(sportService.findSportById(sport1DTO.getIdSport())).thenReturn(sport1);
        when(beanMappingService.mapTo(sportList, SportDTO.class)).thenReturn(sportDTOList);
        
        sportFacade.updateSportResults(sport1DTO);
        
        verify(sportService).updateSportResults(sport1);
    }
}
