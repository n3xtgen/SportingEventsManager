package cz.muni.fi.PA165.service.facade;

import cz.muni.fi.PA165.dto.CreateSportDTO;
import cz.muni.fi.PA165.dto.SportDTO;
import cz.muni.fi.PA165.dto.facade.SportFacade;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.service.BeanMappingService;
import cz.muni.fi.PA165.service.EventService;
import cz.muni.fi.PA165.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by Vladimir on 25.11.2015.
 */
@Service
@Transactional
public class SportFacadeImpl implements SportFacade {

    @Autowired
    private SportService sportService;

    @Autowired
    private EventService eventService;

    @Autowired
    private BeanMappingService beanMappingService;

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
    public void updateSport(SportDTO s) {
        Sport sport = sportService.findSportById(s.getIdSport());
        sport.setName(s.getName());
        sportService.updateSport(sport);
    }

    @Override
    public Collection<SportDTO> getAllSports() {
        return beanMappingService.mapTo(sportService.getAllSports(), SportDTO.class);
    }

    @Override
    public void addNewSport(CreateSportDTO s) {
        Sport sport = new Sport();
        sport.setName(s.getName());
        sport.setEvent(eventService.findEventById(s.getEvent())); // add event reference
        sportService.addNewSport(sport);
    }
}
