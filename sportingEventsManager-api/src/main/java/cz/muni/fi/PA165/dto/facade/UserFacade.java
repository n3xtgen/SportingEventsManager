package cz.muni.fi.PA165.dto.facade;

import cz.muni.fi.PA165.dto.CreateUserDTO;
import java.util.Collection;

import cz.muni.fi.PA165.dto.UserAuthenticateDTO;
import cz.muni.fi.PA165.dto.UserDTO;


/**
 * 
 * @author jbouska
 */

public interface UserFacade {
	
    /**
     * Find sportsman by ID
     * @param userId 
     * @return 
     */
	UserDTO findUserById(Long userId);
        
        /**
         * Find Sportsman by email
         * @param email
         * @return 
         */
        UserDTO findUserByEmail(String email);
        
        /**
         * Update sportsman
         * @param s 
         */
        void updateUser(UserDTO s);

	/**
	 * Register the sportsman with the unencrypted password.
	 */
	long registerUser(CreateUserDTO sp);

	/**
	 * Get all registered sportsmans
	 */
	Collection<UserDTO> getAllUsers();

	/**
	 * Try to authenticate a user. Return true only if the hashed password matches the records.
	 */
	boolean authenticate(UserAuthenticateDTO s);
        
        

}
