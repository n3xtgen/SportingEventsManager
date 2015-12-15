package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.Exceptions.DataAccessException;
import cz.muni.fi.PA165.dao.EntryDao;
import cz.muni.fi.PA165.dao.SportDao;
import cz.muni.fi.PA165.dao.SportsmanDao;
import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Sportsman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author n3xtgen
 */
@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private SportDao sportDao;

    @Autowired
    private SportsmanDao sportsmanDao;

    @Autowired
    private EntryDao entryDao;

    @Override
    public void createEntry(Entry entry) {
        try {
            entryDao.create(entry);
        } catch(Exception ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public void updateEntry(Entry entry) {
        try {
            entryDao.update(entry);
        } catch(Exception ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public void deleteEntry(Entry entry) {
        try {
            entryDao.delete(entry);
        } catch(Exception ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public Collection<Entry> getAllEntries() {
        try {
            return entryDao.findAll();
        } catch(Exception ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public Entry findEntryById(Long id) {
        try {
            return entryDao.findById(id);
        } catch(Exception ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public Collection<Entry> findEntriesBySport(Sport sport) {
        try {
            return entryDao.findBySport(sport);
        } catch(Exception ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public Collection<Entry> findEntriesBySportsman(Sportsman sportsman) {
        try {
            return entryDao.findBySportsman(sportsman);
        } catch(Exception ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public Entry findEntryBySportAndSportsman(Sport sport, Sportsman sportsman) {
        try{
            return entryDao.findBySportAndSportsman(sport, sportsman);
        }catch(Exception ex){
            throw new DataAccessException(ex);
        }
    }
}