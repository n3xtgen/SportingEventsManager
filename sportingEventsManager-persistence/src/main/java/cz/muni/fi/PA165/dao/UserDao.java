package cz.muni.fi.PA165.dao;

import java.util.List;

import cz.muni.fi.PA165.entity.Usr;

/**
 *
 * @author jbouska
 */
public interface UserDao {

	/**
	 *
	 * @param id
	 * @return sportsman or null if the sportsman does not exist
	 */
	public Usr findById(Long id);

	/**
	 *
	 * @param s sportsman to create
	 */
	public void create(Usr s);

	/**
	 *
	 * @param s Usr
	 * @return Usr
	 */
	public Usr update(Usr s);

	/**
	 *
	 * @param s sportsman to delete
	 * @throws cz.muni.fi.PA165.dao.exception.DataAccessException if there is a problem on persistent layer
	 */
	public void delete(Usr s);

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
	public List<Usr> findBySurname(String surname);

        /**
	 *
	 * @param email
	 * @return sportsmans with selected email
         */
        public Usr findByEmail(String email);


        
	

}
