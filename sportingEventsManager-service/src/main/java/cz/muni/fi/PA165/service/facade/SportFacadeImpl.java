package cz.muni.fi.PA165.service.facade;

import cz.muni.fi.PA165.dto.*;
import cz.muni.fi.PA165.dto.facade.SportFacade;
import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Created by Vladimir on 25.11.2015.
 */
@Service
@Transactional
public class SportFacadeImpl implements SportFacade {

    private final SportService sportService;

    private final EventService eventService;
    
    private final EntryService entryService;

    private final BeanMappingService beanMappingService;

    @Autowired
    public SportFacadeImpl(SportService sportService, EventService eventService, EntryService entryService, BeanMappingService beanMappingService) {
        this.sportService = sportService;
        this.eventService = eventService;
        this.entryService = entryService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public void addNewSport(CreateSportDTO s) {
        Sport sport = new Sport();
        sport.setName(s.getName());
        sport.setStartTime(s.getStartTime());
        sport.setEndTime(s.getEndTime());
        sport.setEvent(eventService.findEventById(s.getEvent().getIdEvent())); // add event reference
        s.setIdSport(sportService.addNewSport(sport));
    }
    
    @Override
    public void updateSport(SportDTO s) {
        Sport sport = sportService.findSportById(s.getIdSport());
        sport.setName(s.getName());
        sportService.updateSport(sport);
    }
    
    @Override
    public SportDTO findSportById(Long id) {
        Sport sport = sportService.findSportById(id);
        return (sport == null) ? null : beanMappingService.mapTo(sport, SportDTO.class);
    }

    @Override
    public SportDTO findSportByName(String name) {
        Sport sport = sportService.findSportByName(name);
        return (sport == null) ? null : beanMappingService.mapTo(sport, SportDTO.class);
    }
    
    @Override
    public Collection<SportDTO> getAllSports() {
        return beanMappingService.mapTo(sportService.getAllSports(), SportDTO.class);
    }
    
    @Override
    public void updateSportResults(SportDTO sportDTO) {
        Sport sport = sportService.findSportById(sportDTO.getIdSport());
        List<EntryDTO> entriesDTO = sportDTO.getEntries();
        for (EntryDTO entryDTO : entriesDTO) {
            Entry entry = sport.getEntries().stream().filter(e -> e.getIdEntry().equals(entryDTO.getIdEntry())).findFirst().get();
            entry.setEntryState(Entry.EntryState.values()[entryDTO.getEntryState().ordinal()]);
            entry.setTime(entryDTO.getTime());
        }
        sportService.updateSportResults(sport);
    }
}
