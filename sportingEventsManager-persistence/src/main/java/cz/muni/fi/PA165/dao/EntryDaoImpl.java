package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Entry;
import cz.muni.fi.PA165.entity.Sport;
import cz.muni.fi.PA165.entity.Sportsman;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author n3xtgen
 */
@Repository
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
    public List<Entry> findBySportsman(Sportsman sportsman) {
        return entityManager.createQuery("SELECT e FROM Entry e WHERE e.sportsman = :sportsman", Entry.class)
                .setParameter("sportsman", sportsman)
                .getResultList();
    }
}
