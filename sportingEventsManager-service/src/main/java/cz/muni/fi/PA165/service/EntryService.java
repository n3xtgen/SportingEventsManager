package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Sportsman;

import java.util.Collection;

/**
 * @author n3xtgen
 */
public interface EntryService {

    void createEntry(Entry entry);

    void updateEntry(Entry entry);

    void deleteEntry(Entry entry);

    Collection<Entry> getAllEntries();

    Entry findEntryById(Long id);

    Collection<Entry> findEntriesBySport(Sport sport);

    Collection<Entry> findEntriesBySportsman(Sportsman sportsman);
}
