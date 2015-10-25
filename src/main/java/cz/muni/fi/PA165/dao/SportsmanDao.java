package cz.muni.fi.PA165.dao;

import java.util.List;

import cz.muni.fi.PA165.entity.Event;
import cz.muni.fi.PA165.entity.Sportsman;

/**
 *
 * @author jbouska
 */
public interface SportsmanDao {

	/**
	 *
	 * @param id
	 * @return sportsman
	 * @throws NoResultException if there is no result
	 */
	public Sportsman findById(Long id);

	/**
	 *
	 * @param s sportsman to create
	 */
	public void create(Sportsman s);

	/**
	 *
	 * @param s sportsman to delete
	 */
	public void delete(Sportsman s);

	/**
	 *
	 * @return all artifact from database
	 */
	public List findAll();

	/**
	 *
	 * @param surname
	 * @return sportsman with selected surname
	 * @throws NoResultException if there is no result
	 */
	public List<Sportsman> findBySurname(String surname);

	/**
	 *
	 * @param id
	 * @return sportsman with selected ID
	 * @throws NoResultException if there is no result
	 *
	 */
	public Sportsman findByPersonalID(String id);

	/**
	 *
	 * @return list of all sportsman's events
	 */
	public List<Event> findAllEvents(Sportsman s);
}
