package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Sport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Vladimir
 */
@Repository
public class SportDaoImpl implements SportDao {

    @PersistenceContext
    private EntityManager em;

    public Sport findById(long id) {
        return em.find(Sport.class, id);
    }

    public void create(Sport s) {
        em.persist(s);
    }

    public void delete(Sport s) {
        em.remove(s);
    }

    public List<Sport> findAll() {
        return em.createQuery("SELECT s FROM Sport s", Sport.class).getResultList();
    }

    public Sport findByName(String name) {
        try {
            return em.createQuery("SELECT s FROM Sport s WHERE s.name = :name",Sport.class).setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }
}
