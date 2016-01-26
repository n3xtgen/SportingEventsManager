package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.Exceptions.DataAccessException;
import cz.muni.fi.PA165.dao.EntryDao;
import cz.muni.fi.PA165.dao.SportDao;
import cz.muni.fi.PA165.dao.UserDao;
import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Usr;

import cz.muni.fi.PA165.service.constant.SLayerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author n3xtgen
 */
@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private SportDao sportDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private EntryDao entryDao;

    @Override
    public Long createEntry(Entry entry) {
        // a man can only sign up for a sport before it starts
        if((new Date()).compareTo(entry.getSport().getStartTime()) >= 0)
            return SLayerConstants.ERROR_SPORT_ALREADY_STARTED;
        // check for a time collision
        if(!isUserFreeForSport(entry.getUsr(), entry.getSport()))
            return SLayerConstants.ERROR_TIME_CONFLICT;

        try {
            entryDao.create(entry);
        } catch(Exception ex) {
            throw new DataAccessException(ex);
        }
        return entry.getIdEntry();
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
    public boolean deleteEntry(Entry entry) {
        // dont allow anybody to quit from a sport after the start
        if((new Date()).compareTo(entry.getSport().getStartTime()) >= 0)
            return false;

        try {
            entryDao.delete(entry);
        } catch(Exception ex) {
            throw new DataAccessException(ex);
        }
        return true;
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
    public Collection<Entry> findEntriesBySportsman(Usr user) {
        try {
            return entryDao.findByUser(user);
        } catch(Exception ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public Entry findEntryBySportAndSportsman(Sport sport, Usr user) {
        try{
            return entryDao.findBySportAndUser(sport, user);
        }catch(Exception ex){
            throw new DataAccessException(ex);
        }
    }

    /**
     * Check if the user can sign up to a sport, since he can´t be assigned to more sports that take place
     * at the same time
     *
     * @brief what we do here is that we go through all user´s registrations and check if the sport the
     *        he wants to sign to starts or ends within time range of any of his already registered sports
     *
     * @param usr
     * @return
     */
    private boolean isUserFreeForSport(Usr usr, Sport sport){
        List<Entry> entries = entryDao.findByUser(usr);
        if(entries != null && !entries.isEmpty()){
            for(Entry itr : entries){
                Sport toCompare = itr.getSport();
                if(((toCompare.getStartTime().compareTo(sport.getStartTime()) <= 0) && (toCompare.getEndTime().compareTo(sport.getStartTime()) >= 0)) ||
                        ((toCompare.getStartTime().compareTo(sport.getStartTime()) >= 0) && (toCompare.getStartTime().compareTo(sport.getEndTime()) <= 0)))
                    return false;
            }
        }

        return true;
    }

}
