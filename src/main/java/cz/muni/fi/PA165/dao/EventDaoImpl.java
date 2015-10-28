package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.dao.exception.DataAccessException;
import cz.muni.fi.PA165.entity.Event;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Jamik on 28.10.2015.
 */
@Repository
public class EventDaoImpl implements EventDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Event evt) {
        try{
            entityManager.persist(evt);
        }catch(Throwable tr){
            throw new DataAccessException("Problem with creating of event: " + evt, tr);
        }
    }

    public void delete(Event evt) {
        try{
            entityManager.remove(evt);
        }catch(Throwable tr){
            throw new DataAccessException("Problem with deleting of event: " + evt, tr);
        }
    }

    // TODO: implement or not? or maybe implement using basic queries...
    public Event update(Event evt) {
        return entityManager.merge(evt);
    }

    public Event findById(Long id) {
        try{
            return entityManager.createQuery("SELECT e FROM Event e WHERE e.id = :id", Event.class).setParameter("id", id).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }catch(Throwable tr){
            throw new DataAccessException("Can´t find event by its ID: " + id, tr);
        }
    }

    public List<Event> findAll() {
        try{
            return entityManager.createQuery("SELECT e FROM Event e", Event.class).getResultList();
        }catch (NoResultException nre){
            return null;
        }catch(Throwable tr){
            throw new DataAccessException("Can´t find any records", tr);
        }
    }
}
