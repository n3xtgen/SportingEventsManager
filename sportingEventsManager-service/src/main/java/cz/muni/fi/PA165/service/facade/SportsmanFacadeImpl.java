package cz.muni.fi.PA165.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import cz.muni.fi.PA165.dto.SportsmanAuthenticateDTO;
import cz.muni.fi.PA165.dto.SportsmanDTO;
import cz.muni.fi.PA165.dto.facade.SportsmanFacade;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Sportsman;
import cz.muni.fi.PA165.service.SportsmanService;
import cz.muni.fi.PA165.service.BeanMappingService;

@Service
@Transactional
public class SportsmanFacadeImpl implements SportsmanFacade {

    @Autowired
    private SportsmanService sportsmanService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public SportsmanDTO findSportsmanById(Long id) {
        Sportsman user = sportsmanService.findSportsmanById(id);
        return (user == null) ? null : beanMappingService.mapTo(user, SportsmanDTO.class);
    }

    @Override
    public SportsmanDTO findSportsmanByCitizenId(String id) {
        Sportsman user = sportsmanService.findSportsmanByCitizenID(id);
        return (user == null) ? null : beanMappingService.mapTo(user, SportsmanDTO.class);
    }



    @Override
    public void registerSportsman(SportsmanDTO sportsmanDTO, String unencryptedPassword) {
        Sportsman sportsmanEntity = beanMappingService.mapTo(sportsmanDTO, Sportsman.class);
        sportsmanService.registerSportsman(sportsmanEntity, unencryptedPassword);
        sportsmanDTO.setId(sportsmanEntity.getIdSportsman());
    }

    @Override
    public Collection<SportsmanDTO> getAllSportsmans() {
        return beanMappingService.mapTo(sportsmanService.getAllSportsmans(), SportsmanDTO.class);
    }

    @Override
    public boolean authenticate(SportsmanAuthenticateDTO s) {
        return sportsmanService.authenticate(
                sportsmanService.findSportsmanById(s.getUserId()), s.getPassword());
    }

}
