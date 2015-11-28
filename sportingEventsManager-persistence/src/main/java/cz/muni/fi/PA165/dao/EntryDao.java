package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Sportsman;

import java.util.List;

/**
 * @author n3xtgen
 */
public interface EntryDao {

    public void create(Entry entry);

    public void delete(Entry entry);

    public Entry update(Entry entry);

    public List<Entry> findAll();

    public Entry findById(Long id);

    public List<Entry> findBySport(Sport sport);

    public List<Entry> findBySportsman(Sportsman sportsman);

}
