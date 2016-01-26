package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.Exceptions.DataAccessException;
import cz.muni.fi.PA165.dao.SportDao;
import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Vladimir on 25.11.2015.
 */
@Service
public class SportServiceImpl implements SportService{

    @Autowired
    SportDao sportDao;

    @Override
    public Sport findSportById(Long id) {
        try {
            return sportDao.findById(id);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public Sport findSportByName(String name) {
        try {
            return sportDao.findByName(name);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public void updateSport(Sport s) {
        try {
            sportDao.update(s);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public Collection<Sport> getAllSports() {
        try {
            return sportDao.findAll();
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public void deleteSport(Sport s) {
        try {
            sportDao.delete(s);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public Long addNewSport(Sport s) {
        try {
            sportDao.create(s);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return s.getIdSport();
    }
    
    @Override
    public void updateSportResults(Sport sport) {
        try {
            Set<Entry> entries = sport.getEntries();
            
            // nastavit vsem nedokoncenym pozici 0 a cas null
            entries.stream().filter(e -> e.getEntryState() != Entry.EntryState.FINISHED).forEach( e -> {
                e.setPosition(0);
                e.setTime(null);
            });
            
            // nastavit vsem dokoncenym pozici podle casu
            AtomicInteger index = new AtomicInteger();
            entries.stream().filter(e -> e.getEntryState() == Entry.EntryState.FINISHED).sorted(new Comparator<Entry> () {
                @Override
                public int compare(Entry o1, Entry o2) {
                    return o1.getTime().compareTo(o2.getTime());
                }
            }).forEach(e -> e.setPosition(index.incrementAndGet()));
            sportDao.update(sport);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }
}
