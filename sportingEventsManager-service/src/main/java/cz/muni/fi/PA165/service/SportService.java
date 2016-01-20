package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.entity.Sport;

import java.util.Collection;

/**
 * Created by Vladimir on 25.11.2015.
 */
public interface SportService {

    /**
     * Find sport by ID
     *
     * @param id
     * @return
     */
    Sport findSportById(Long id);

    /**
     * Find sport by name
     *
     * @param name
     * @return
     */
    Sport findSportByName(String name);

    /**
     * Update sport (name)
     *
     * @param s
     */
    void updateSport(Sport s);

    /**
     * Get all sports
     *
     * @return
     */
    Collection<Sport> getAllSports();

    /**
     * Delete the sport
     *
     * @param s
     */
    void deleteSport(Sport s);

    /**
     * Create new sport
     * @param s
     * @return
     */
    Long addNewSport(Sport s);
    
    /**
     * Updates sport results and positions.
     * 
     * @param s 
     */
    public void updateSportResults(Sport s);
}
