package cz.muni.fi.PA165.dto.facade;

import java.util.Collection;

import cz.muni.fi.PA165.dto.SportsmanAuthenticateDTO;
import cz.muni.fi.PA165.dto.SportsmanDTO;

public interface SportsmanFacade {
	
	SportsmanDTO findSportsmanById(Long userId);

	SportsmanDTO findSportsmanByCitizenId(String id);

	/**
	 * Register the given user with the given unencrypted password.
	 */
	void registerSportsman(SportsmanDTO sportsmanDTO, String unencryptedPassword);

	/**
	 * Get all registered sportsmans
	 */
	Collection<SportsmanDTO> getAllSportsmans();

	/**
	 * Try to authenticate a user. Return true only if the hashed password matches the records.
	 */
	boolean authenticate(SportsmanAuthenticateDTO s);

}
