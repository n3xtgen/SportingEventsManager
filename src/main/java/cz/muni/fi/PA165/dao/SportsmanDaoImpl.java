
package cz.muni.fi.PA165.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import cz.muni.fi.PA165.entity.Sportsman;

/**
 *
 * @author jbouska
 */
@Repository
public class SportsmanDaoImpl implements SportsmanDao{

    @PersistenceContext
    private EntityManager em;

    public Sportsman findById(Long id) {
        return em.find(Sportsman.class, id);
    }

    public void create(Sportsman s) {
        em.persist(s);
    }

    public void delete(Sportsman s) {
        em.remove(s);
    }

    public List<Sportsman> findAll() {
        return em.createQuery("SELECT s FROM Sportsman s", Sportsman.class).getResultList();
    }

    public List<Sportsman> findBySurname(String surname) {

        return em.createQuery("SELECT s FROM Sportsman s WHERE s.name = :name", Sportsman.class)
                .setParameter("name", surname).getResultList();

    }

    public Sportsman findByCitizenIdNumber(String id) {

        return em.createQuery("select s from Sportsman s where s.citizenIdNumber = :id", Sportsman.class)
                .setParameter("id", id).getSingleResult();
    }


}
