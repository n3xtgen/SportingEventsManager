package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Usr;

import java.util.Collection;

/**
 * @author n3xtgen
 */
public interface EntryService {

    /**
     * Create new entry
     * @param entry
     */
    Long createEntry(Entry entry);

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
     * Find entry by Usr
     * @param Usr
     * @return set of entries
     */
    Collection<Entry> findEntriesBySportsman(Usr Usr);

    /**
     * Find entry by combination of sport and Usr
     * @param sport
     * @param Usr
     * @return
     */
    Entry findEntryBySportAndSportsman(Sport sport, Usr Usr);
}
