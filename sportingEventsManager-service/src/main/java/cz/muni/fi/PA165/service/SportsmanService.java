package cz.muni.fi.PA165.service;

import java.util.List;

import cz.muni.fi.PA165.entity.Sportsman;


/**
 * 
 * @author jbouska
 */
public interface SportsmanService {

	void registerSportsman(Sportsman u, String unencryptedPassword);

	List<Sportsman> getAllSportsmans();

	boolean authenticate(Sportsman u, String password);

	Sportsman findSportsmanById(Long sportsmanId);

	List<Sportsman> findSportsmanBySurname(String surname);

	Sportsman findSportsmanByCitizenID(String citizenID);
        
        Sportsman findSportsmanByEmail(String email);

}
