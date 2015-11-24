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
	
    /**
     * Find sportsman by ID
     * @param userId 
     * @return 
     */
	SportsmanDTO findSportsmanById(Long userId);
        
        /**
         * Find Sportsman by email
         * @param email
         * @return 
         */
        SportsmanDTO findSportsmanByEmail(String email);
        
        /**
         * Update sportsman
         * @param s 
         */
        void updateSportsman(SportsmanDTO s);

	/**
	 * Register the sportsman with the unencrypted password.
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
