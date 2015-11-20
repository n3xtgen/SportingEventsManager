package cz.muni.fi.PA165.dto.facade;

import cz.muni.fi.PA165.dto.CreateSportsmanDTO;
import java.util.Collection;

import cz.muni.fi.PA165.dto.SportsmanAuthenticateDTO;
import cz.muni.fi.PA165.dto.SportsmanDTO;


/**
 * 
 * @author jbouska
 */

public interface SportsmanFacade {
	
	SportsmanDTO findSportsmanById(Long userId);

	SportsmanDTO findSportsmanByCitizenId(String id);
        
        SportsmanDTO findSportsmanByEmail(String email);

	/**
	 * Register the given user with the given unencrypted password.
	 */
	long registerSportsman(CreateSportsmanDTO sp);

	/**
	 * Get all registered sportsmans
	 */
	Collection<SportsmanDTO> getAllSportsmans();

	/**
	 * Try to authenticate a user. Return true only if the hashed password matches the records.
	 */
	boolean authenticate(SportsmanAuthenticateDTO s);
        
        

}
