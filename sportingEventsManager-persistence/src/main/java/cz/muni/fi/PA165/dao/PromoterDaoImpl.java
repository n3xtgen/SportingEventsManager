package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Promoter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author N3xtGeN
 */
@Repository
public class PromoterDaoImpl implements PromoterDao {

    @PersistenceContext
    private EntityManager em;

    /**
     * Vytvori promotera.
     *
     * @param promoter Promoter
     */
    @Override
    public void create(Promoter promoter) {
        em.persist(promoter);
    }

    /**
     * Smaze promotera.
     *
     * @param promoter Promoter
     */
    @Override
    public void delete(Promoter promoter) {
        em.remove(promoter);
    }

    /**
     * Updatuje promotera.
     *
     * @param promoter Promoter
     * @return Promoter
     */
    @Override
    public Promoter update(Promoter promoter) {
        return em.merge(promoter);
    }

    /**
     * Vypise vsechny promotery.
     *
     * @return Vsichni promoteri
     */
    @Override
    public List<Promoter> findAll() {
        return em.createQuery("SELECT p from Promoter p", Promoter.class).getResultList();
    }

    /**
     * Najde promotera pomoci id.
     *
     * @param id Id promotera k vyhledani
     * @return Promoter s danym id
     */
    @Override
    public Promoter findById(Long id) {
        return em.find(Promoter.class, id);
    }

    /**
     * Najde promotera pomoci prijmeni.
     *
     * @param surname Prijmeni promotera k vyhledani
     * @return Promoter s danym prijmenim
     */
    @Override
    public Promoter findBySurname(String surname) {
        try {
            return em.createQuery("SELECT p FROM Promoter p where p.surname = :surname", Promoter.class)
                    .setParameter("surname", surname).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    /**
     * Najde promotera pomoci rodneho cisla.
     *
     * @param citizenIdNumber Rodne cislo
     * @return Promoter se zadanym rodnym cislem
     *
     */
    @Override
    public Promoter findByCitizenIdNumber(String citizenIdNumber) {
        try {
            return em.createQuery("SELECT p FROM Promoter p where p.citizenIdNumber = :citizenIdNumber", Promoter.class)
                    .setParameter("citizenIdNumber", citizenIdNumber).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
