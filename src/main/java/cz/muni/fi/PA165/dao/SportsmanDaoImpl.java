
package cz.muni.fi.PA165.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import java.util.List;

import cz.muni.fi.PA165.dao.exception.DataAccessException;
import cz.muni.fi.PA165.entity.Event;
import cz.muni.fi.PA165.entity.Sportsman;

/**
 *
 * @author jbouska
 */
@Repository
public class SportsmanDaoImpl implements SportsmanDao{
    
     @PersistenceContext
	private EntityManager em;
    
    @Override
    public Sportsman findById(Long id) {
        return em.find(Sportsman.class, id);
    }

    @Override
    public void create(Sportsman s) {
        try {
            em.persist(s);
        } catch (Throwable tr) {
            throw new DataAccessException("Problem with creating sportsman:" + s, tr);
        }
    }

    @Override
    public void delete(Sportsman s) {
        try {
        em.remove(s);
        } catch (Throwable tr) {
            throw new DataAccessException("Problem with deleting sportsman:" + s, tr);
        }
    }

    @Override
    public List<Sportsman> findAll() {
        try {
            return em.createQuery("SELECT s FROM Sportsman s", Sportsman.class).getResultList();
        } catch (Throwable tr) {
            throw new DataAccessException("Problem in find all sportsmans", tr);
        }
    }

    @Override
    public List<Sportsman> findBySurname(String surname) {
        try {
            return em.createQuery("SELECT s FROM Sportsman s WHERE s.name = :name", Sportsman.class)
                    .setParameter("name", surname).getResultList();
        } catch (Throwable tr) {
            throw new DataAccessException("Problem with find sportsman by surname: " + surname, tr);
        }
    }

    @Override
    public Sportsman findByPersonalID(String id) {
        try {
            return em.createQuery("select s from Sportsman s where s.personID = :id", Sportsman.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } catch (Throwable tr) {
            throw new DataAccessException("Problem with find sportsman by personal id: " + id, tr);
        }
    }

    @Override
    public List<Event> findAllEvents(Sportsman s) {
      //TODO jbouska after Event class definition
       return null;
    }


}
