package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Event;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Date;

/**
 * @author Jamik (Lukas Gryc)
 */
@Repository
public class EventDaoImpl implements EventDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Event evt) {
        entityManager.persist(evt);
    }

    @Override
    public void delete(Event evt) {
        entityManager.remove(evt);
    }

    // TODO: implement or not? or maybe implement using basic queries...
    @Override
    public Event update(Event evt) {
        return entityManager.merge(evt);
    }

    @Override
    public Event findById(Long id) {
        return entityManager.createQuery("SELECT e FROM Event e WHERE e.id = :id", Event.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public Event findByName(String name){
        return entityManager.createQuery("SELECT e FROM Event e WHERE e.name = :name", Event.class).setParameter("name", name).getSingleResult();
    }

    @Override
    public List<Event> findAll() {
        return entityManager.createQuery("SELECT e FROM Event e", Event.class).getResultList();
    }

    // TODO: how about events that start or end outside of the interval
    @Override
    public List<Event> findEventsInDateRange(Date startDate, Date endDate){
        TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e WHERE e.startTime >= :sDate AND e.endTime <= :eDate", Event.class);
        query.setParameter("sDate", startDate);
        query.setParameter("eDate", endDate);
        return query.getResultList();
    }
}
