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
    public void updateSportResults(Sport s) {
        try {
            AtomicInteger index = new AtomicInteger();
            s.getEntries().stream().sorted(new Comparator<Entry> () {
                @Override
                public int compare(Entry o1, Entry o2) {
                    Entry.EntryState entryState1 = o1.getEntryState();
                    Entry.EntryState entryState2 = o2.getEntryState();
                    
                    if (entryState1.ordinal() > entryState2.ordinal()) { // napr. pokud je jeden Finished a druhy Registered (zatim nedobehl), tak prvni bude pred nim
                        return -1;
                    } else if (entryState1.ordinal() < entryState2.ordinal()) {
                        return 1;
                    } else { // pokud maji stejnej stav
                        if (entryState1 == Entry.EntryState.FINISHED) { // pokud dokoncili radim podle casu
                            return o1.getTime().compareTo(o2.getTime()); // v tom pripade musi byt cas vyplnenej a nemusim se bat ze ze getTime() je null
                        } else if ((entryState1 == Entry.EntryState.REGISTERED) || (entryState1 == Entry.EntryState.DISQUALIFIED)) { // pokud zatim nedokoncili | diskvalifikovani
                            return o1.getUsr().getSurname().compareTo(o2.getUsr().getSurname()); // seradim podle prijmeni
                        } else {
                            throw new IllegalStateException("Comparing entries with unknown state.");
                        }
                    }
                }
            }).forEach(e -> e.setPosition(index.incrementAndGet()));
            sportDao.update(s);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }
}
