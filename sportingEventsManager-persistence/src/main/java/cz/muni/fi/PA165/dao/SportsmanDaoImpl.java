
package cz.muni.fi.PA165.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import cz.muni.fi.PA165.entity.Sportsman;
import javax.persistence.NoResultException;

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
    public Sportsman update(Sportsman s) { return em.merge(s); }

    @Override
    public void delete(Sportsman s) {
        em.remove(s);
    }

    @Override
    public List<Sportsman> findAll() {
        return em.createQuery("SELECT s FROM Sportsman s", Sportsman.class).getResultList();
    }

    @Override
    public List<Sportsman> findBySurname(String surname) {

        return em.createQuery("SELECT s FROM Sportsman s WHERE s.name = :name", Sportsman.class)
                .setParameter("name", surname).getResultList();

    }
    
     @Override
    public Sportsman findByEmail(String email) {
        try{
       return em.createQuery("select s from Sportsman s where s.email = :email", Sportsman.class)
                .setParameter("email", email).getSingleResult();
       } catch (NoResultException nrf) {
			return null;
		}
    }

   
  


}
