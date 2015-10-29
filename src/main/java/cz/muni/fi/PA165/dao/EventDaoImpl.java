package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.dao.exception.DataAccessException;
import cz.muni.fi.PA165.entity.Event;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by Jamik on 28.10.2015.
 */
@Repository
public class EventDaoImpl implements EventDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Event evt) {
        entityManager.persist(evt);
    }

    public void delete(Event evt) {
        entityManager.remove(evt);
    }

    // TODO: implement or not? or maybe implement using basic queries...
    public Event update(Event evt) {
        return entityManager.merge(evt);
    }

    public Event findById(Long id) {
        return entityManager.createQuery("SELECT e FROM Event e WHERE e.id = :id", Event.class).setParameter("id", id).getSingleResult();
    }

    public List<Event> findAll() {
        return entityManager.createQuery("SELECT e FROM Event e", Event.class).getResultList();
    }
}
