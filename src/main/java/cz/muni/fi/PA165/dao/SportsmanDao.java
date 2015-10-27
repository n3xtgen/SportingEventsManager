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
	 * @return sportsman or null if the sportsman does not exist
	 * @throws IllegalArgumentException if the first argument does not denote an entity type or
	 * 			the second argument is is not a valid type for that entity’s primary key or is null
	 *
	 */
	public Sportsman findById(Long id);

	/**
	 *
	 * @param s sportsman to create
	 * @throws cz.muni.fi.PA165.dao.exception.DataAccessException if there is a problem on persistent layer
	 */
	public void create(Sportsman s);

	/**
	 *
	 * @param s sportsman to delete
	 * @throws cz.muni.fi.PA165.dao.exception.DataAccessException if there is a problem on persistent layer
	 */
	public void delete(Sportsman s);

	/**
	 *
	 * @return all artifact from database
	 * @throws cz.muni.fi.PA165.dao.exception.DataAccessException if there is a problem on persistent layer
	 */
	public List findAll();

	/**
	 *
	 * @param surname
	 * @return sportsmans with selected surname
	 * @throws cz.muni.fi.PA165.dao.exception.DataAccessException if there is a problem on persistent layer
	 */
	public List<Sportsman> findBySurname(String surname);

	/**
	 *
	 * @param id
	 * @return sportsman with selected ID or null if the sportsman does not exist
	 * @throws cz.muni.fi.PA165.dao.exception.DataAccessException if there is a problem on persistent layer
	 *
	 */
	public Sportsman findByPersonalID(String id);

	/**
	 *
	 * @return list of all sportsman's events
	 */
	public List<Event> findAllEvents(Sportsman s);
}
