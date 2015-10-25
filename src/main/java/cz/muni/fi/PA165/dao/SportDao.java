package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Sport;

import java.util.List;

/**
 * Created by Vladimir on 25.10.2015.
 */

public interface SportDao {
    public Sport findById(long id);
    public void create(Sport s);
    public void delete(Sport s);
    public List<Sport> findAll();
    public Sport findByName(String name);
}
