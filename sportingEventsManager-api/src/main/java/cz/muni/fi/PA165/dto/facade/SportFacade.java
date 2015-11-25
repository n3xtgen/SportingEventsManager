package cz.muni.fi.PA165.dto.facade;

import cz.muni.fi.PA165.dto.CreateSportDTO;
import cz.muni.fi.PA165.dto.SportDTO;

import java.util.Collection;

/**
 * Created by Vladimir on 24.11.2015.
 */
public interface SportFacade {
    /**
     * Find sport by ID
     *
     * @param id
     * @return
     */
    SportDTO findSportById(Long id);

    /**
     * Find sport by name
     *
     * @param name
     * @return
     */
    SportDTO findSportByName(String name);

    /**
     * Update sport (name)
     *
     * @param s
     */
    void updateSport(SportDTO s);

    /**
     * Get all sports
     *
     * @return
     */
    Collection<SportDTO> getAllSports();

    /**
     * Create new sport
     *
     * @param s
     */
    void addNewSport(CreateSportDTO s);
}
