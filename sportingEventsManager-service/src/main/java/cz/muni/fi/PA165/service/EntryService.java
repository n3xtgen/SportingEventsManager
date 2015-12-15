package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Sportsman;

import java.util.Collection;

/**
 * @author n3xtgen
 */
public interface EntryService {

    /**
     * Create new entry
     * @param entry
     */
    void createEntry(Entry entry);

    /**
     * Update entry
     * @param entry
     */
    void updateEntry(Entry entry);

    /**
     * Delete entry
     * @param entry
     */
    void deleteEntry(Entry entry);

    /**
     * Get all entries
     * @return set of entries
     */
    Collection<Entry> getAllEntries();

    /**
     * Find entry by its id
     * @param id
     * @return entry
     */
    Entry findEntryById(Long id);

    /**
     * Find entry by sport
     * @param sport
     * @return set of entries
     */
    Collection<Entry> findEntriesBySport(Sport sport);

    /**
     * Find entry by sportsman
     * @param sportsman
     * @return set of entries
     */
    Collection<Entry> findEntriesBySportsman(Sportsman sportsman);

    /**
     * Find entry by combination of sport and sportsman
     * @param sport
     * @param sportsman
     * @return
     */
    Entry findEntryBySportAndSportsman(Sport sport, Sportsman sportsman);
}
