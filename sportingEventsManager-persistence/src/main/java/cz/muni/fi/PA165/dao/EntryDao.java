package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Sportsman;

import java.util.List;

/**
 * @author n3xtgen
 */
public interface EntryDao {

    /**
     * Create entry
     * @param entry
     */
    public void create(Entry entry);

    /**
     * Delete entry
     * @param entry
     */
    public void delete(Entry entry);

    /**
     * Update entry
     * @param entry
     * @return
     */
    public Entry update(Entry entry);

    /**
     * Find all entries
     * @return
     */
    public List<Entry> findAll();

    /**
     * Find entry by its id
     * @param id
     * @return
     */
    public Entry findById(Long id);

    /**
     * Find entries by sport
     * @param sport
     * @return
     */
    public List<Entry> findBySport(Sport sport);

    /**
     * Find entries by sportsman
     * @param sportsman
     * @return
     */
    public List<Entry> findBySportsman(Sportsman sportsman);

    /**
     * Find entry by combination of sport and sportsman
     * @param sport
     * @param sportsman
     * @return
     */
    public Entry findBySportAndSportsman(Sport sport, Sportsman sportsman);

}
