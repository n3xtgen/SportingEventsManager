package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.Exceptions.DataAccessException;
import cz.muni.fi.PA165.dao.SportDao;
import cz.muni.fi.PA165.entity.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
    public void addNewSport(Sport s) {
        try {
            sportDao.create(s);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }
}
