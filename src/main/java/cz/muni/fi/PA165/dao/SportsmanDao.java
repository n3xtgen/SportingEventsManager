package cz.muni.fi.PA165.dao;

import java.util.List;

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
	 * @throws cz.muni.fi.PA165.dao.exception.DataAccessException if there is a problem on persistent layer
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
	 * @return sportsmans with selected surname
	 */
	public List<Sportsman> findBySurname(String surname);

	/**
	 *
	 * @param id
	 * @return sportsman with selected ID
	 *
	 */
	public Sportsman findByPersonalID(String id);

}
