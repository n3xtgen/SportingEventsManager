package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Sport;

import java.util.List;

/**
 * @author Vladimir
 */

public interface SportDao {
    /**
     * Find sport by ID
     * @param id represent id of sport
     * @return Sport with selected ID
     */
    public Sport findById(long id);

    /**
     * Create new sport
     * @param s represent specific sport
     */
    public void create(Sport s);

    /**
     * Delete existing sport
     * @param s represent specific sport
     */
    public void delete(Sport s);

    /**
     * Find all sports
     * @return all Sports from database
     */
    public List<Sport> findAll();

    /**
     * Find sport by name
     * @param name represent name of sport
     * @return Sport with selected name
     */
    public Sport findByName(String name);
}
