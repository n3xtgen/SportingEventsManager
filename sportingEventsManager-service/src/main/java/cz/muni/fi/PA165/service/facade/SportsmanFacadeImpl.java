package cz.muni.fi.PA165.service.facade;

import cz.muni.fi.PA165.dto.CreateSportsmanDTO;
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


/**
 * 
 * @author jbouska
 */
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
    public SportsmanDTO findSportsmanByEmail(String email) {
        Sportsman user = sportsmanService.findSportsmanByEmail(email);
        return (user == null) ? null : beanMappingService.mapTo(user, SportsmanDTO.class);
    }
    @Override
    public void updateSportsman(SportsmanDTO s){
         Sportsman sp = sportsmanService.findSportsmanById(s.getIdSportsman());
         sp.setName(s.getName());
         sp.setSurname(s.getSurname());
         sportsmanService.updateSportsman(sp);
     }


    @Override
    public long registerSportsman(CreateSportsmanDTO s) {
        
       Sportsman sp = new Sportsman();
       sp.setName(s.getName());
       sp.setSurname(s.getSurname());
       sp.setEmail(s.getEmail());
        sportsmanService.registerSportsman(sp, s.getPassword());
       s.setId(sp.getIdSportsman());
        return sp.getIdSportsman();
    }

    @Override
    public Collection<SportsmanDTO> getAllSportsmans() {
        return beanMappingService.mapTo(sportsmanService.getAllSportsmans(), SportsmanDTO.class);
    }

    @Override
    public boolean authenticate(SportsmanAuthenticateDTO s) {
        return sportsmanService.authenticate(
                sportsmanService.findSportsmanByEmail(s.getEmail()), s.getPassword());
    }

}
