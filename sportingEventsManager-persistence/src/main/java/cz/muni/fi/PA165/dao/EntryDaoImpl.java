package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Usr;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.transaction.Transactional;

/**
 * @author n3xtgen
 */
@Repository
@Transactional
public class EntryDaoImpl implements EntryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Entry entry) {
        entityManager.persist(entry);
    }

    @Override
    public void delete(Entry entry) {
        entityManager.remove(entry);
    }

    @Override
    public Entry update(Entry entry) {
        return entityManager.merge(entry);
    }

    @Override
    public List<Entry> findAll() {
        return entityManager.createQuery("SELECT e FROM Entry e", Entry.class).getResultList();
    }

    @Override
    public Entry findById(Long id) {
        return entityManager.find(Entry.class, id);
    }

    @Override
    public List<Entry> findBySport(Sport sport) {
        return entityManager.createQuery("SELECT e FROM Entry e WHERE e.sport = :sport", Entry.class)
                .setParameter("sport", sport)
                .getResultList();
    }

    @Override
    public List<Entry> findByUser(Usr usr) {
        return entityManager.createQuery("SELECT e FROM Entry e WHERE e.usr = :usr", Entry.class)
                .setParameter("usr", usr)
                .getResultList();
    }

    @Override
    public Entry findBySportAndUser(Sport sport, Usr usr) {
        return entityManager.createQuery("SELECT e FROM Entry e WHERE e.sport = :sport AND e.usr = :usr", Entry.class)
                .setParameter("sport", sport)
                .setParameter("usr", usr)
                .getSingleResult();
    }
}
