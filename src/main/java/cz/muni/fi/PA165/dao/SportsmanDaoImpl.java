
package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Sportsman;
import cz.muni.fi.PA165.entity.Event;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;

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
        em.persist(s);
    }

    @Override
    public void delete(Sportsman s) {
        em.remove(s);
    }

    @Override
    public List<Sportsman> findAll() {
        TypedQuery<Sportsman> query = em.createQuery("SELECT s FROM Sportsman s", Sportsman.class);
				return query.getResultList();
    }

    @Override
    public List<Sportsman> findBySurname(String surname) {
        TypedQuery<Sportsman> query =  em.createQuery("SELECT s FROM Sportsman s WHERE s.name = :name", Sportsman.class).setParameter("name",surname);
        return query.getResultList();
    }

    @Override
    public Sportsman findByPersonalID(String id) {
        return em.createQuery("select s from Sportsman s where s.personID = :id",Sportsman.class).setParameter("id",id).getSingleResult();
    }

    @Override
    public List<Event> findAllEvents(Sportsman s) {
      //TODO jbouska after Event class definition
       return null;
    }


}
