
package cz.muni.fi.PA165.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import cz.muni.fi.PA165.entity.Usr;

import javax.persistence.NoResultException;

/**
 *
 * @author jbouska
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Usr findById(Long id) {
        return em.find(Usr.class, id);
    }

    @Override
    public void create(Usr s) {
        em.persist(s);
    }

    @Override
    public Usr update(Usr s) { return em.merge(s); }

    @Override
    public void delete(Usr s) {
        em.remove(s);
    }

    @Override
    public List<Usr> findAll() {
        return em.createQuery("SELECT s FROM Usr s", Usr.class).getResultList();
    }

    @Override
    public List<Usr> findBySurname(String surname) {

        return em.createQuery("SELECT s FROM Usr s WHERE s.name = :name", Usr.class)
                .setParameter("name", surname).getResultList();

    }
    
     @Override
    public Usr findByEmail(String email) {
        try{
       return em.createQuery("select s from Usr s where s.email = :email", Usr.class)
                .setParameter("email", email).getSingleResult();
       } catch (NoResultException nrf) {
			return null;
		}
    }

   
  


}
