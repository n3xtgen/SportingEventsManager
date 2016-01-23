package cz.muni.fi.PA165.service.facade;

import cz.muni.fi.PA165.dto.CreateEntryDTO;
import cz.muni.fi.PA165.dto.EntryDTO;
import cz.muni.fi.PA165.dto.facade.EntryFacade;
import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Usr;
import cz.muni.fi.PA165.service.BeanMappingService;
import cz.muni.fi.PA165.service.EntryService;
import cz.muni.fi.PA165.service.SportService;
import cz.muni.fi.PA165.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author n3xtgen
 */
@Service
@Transactional
public class EntryFacadeImpl implements EntryFacade {

    private SportService sportService;

    private UserService userService;

    private EntryService entryService;

    private BeanMappingService beanMappingService;

    @Autowired
    public EntryFacadeImpl(SportService sportService, UserService userService, EntryService entryService, BeanMappingService beanMappingService) {
        this.sportService = sportService;
        this.userService = userService;
        this.entryService = entryService;
        this.beanMappingService = beanMappingService;
    }
    
    /**
     * Vytvori novou registraci mezi zavodem a sportovcem.
     *
     * @param createEntryDto
     */
    @Override
    public void registerEntry(CreateEntryDTO createEntryDto) {
        Sport sport = sportService.findSportById(createEntryDto.getSportId());
        Usr user = userService.findById(createEntryDto.getSportsmanId());

        Entry entry = new Entry();
        entry.setSport(sport);
        entry.setUsr(user);
        entry.setEntryState(Entry.EntryState.REGISTERED);
        createEntryDto.setEntryId(entryService.createEntry(entry));
    }

    /**
     * Upravi registraci podle pozadavku.
     *
     * @param entryDto
     */
    @Override
    public void updateEntry(EntryDTO entryDto) {
        entryService.updateEntry(beanMappingService.mapTo(entryDto, Entry.class));
    }

    /**
     * Smaze registraci podle Id.
     *
     * @param id
     */
    @Override
    public void deleteEntry(Long id) {
        entryService.deleteEntry(entryService.findEntryById(id));
    }

    /**
     * Najde registraci podle Id.
     *
     * @param id
     * @return
     */
    @Override
    public EntryDTO findEntryById(Long id) {
        return beanMappingService.mapTo(entryService.findEntryById(id), EntryDTO.class);
    }

    /**
     * Vrati seznam registraci podle Sportu (zavodu).
     *
     * @param sportId
     * @return
     */
    @Override
    public Collection<EntryDTO> findEntriesBySportId(Long sportId) {
        Sport sport = sportService.findSportById(sportId);

        return beanMappingService.mapTo(entryService.findEntriesBySport(sport), EntryDTO.class);
    }

    /**
     * Vrati seznam registraci podle sportovce.
     *
     * @param sportsmanId
     * @return
     */
    @Override
    public Collection<EntryDTO> findEntriesBySportsmanId(Long sportsmanId) {
        Usr user = userService.findById(sportsmanId);

        return beanMappingService.mapTo(entryService.findEntriesBySportsman(user), EntryDTO.class);
    }

    /**
     * Vraci registraci podle kombinace sportu a sportovce
     *
     * @param sportId
     * @param sportsmanId
     * @return
     */
    @Override
    public EntryDTO findEntryBySportsmanAndSportId(Long sportId, Long sportsmanId) {
        Sport sport = sportService.findSportById(sportId);
        Usr user = userService.findById(sportsmanId);

        return beanMappingService.mapTo(entryService.findEntryBySportAndSportsman(sport, user), EntryDTO.class);
    }
}
