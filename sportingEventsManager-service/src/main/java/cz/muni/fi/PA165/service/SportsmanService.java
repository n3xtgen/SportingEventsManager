package cz.muni.fi.PA165.service;

import java.util.List;

import cz.muni.fi.PA165.entity.Sportsman;

/**
 *
 * @author jbouska
 */
public interface SportsmanService {

    /**
     * Register new Sportsman.
     *
     * @param u sportsman to register
     * @param unencryptedPassword
     */
    void registerSportsman(Sportsman u, String unencryptedPassword);

    /**
     * Get all sportsmans.
     *
     * @return list of sportsmans
     */
    List<Sportsman> getAllSportsmans();

    /**
     * Authenticate sportsman.
     *
     * @param u
     * @param password
     * @return true if is authenticate
     */
    boolean authenticate(Sportsman u, String password);

    /**
     * Find sportsman by ID.
     *
     * @param sportsmanId
     * @return
     */
    Sportsman findSportsmanById(Long sportsmanId);

    /**
     * Find sportsman by surname. 
     * 
     * @param surname
     * @return
     */
    List<Sportsman> findSportsmanBySurname(String surname);

    /**
     * Find sportsman by email
     * 
     * @param email
     * @return 
     */
    Sportsman findSportsmanByEmail(String email);

    /**
     * Update sportsman.
     * @param s 
     */
    void updateSportsman(Sportsman s);
    
    /**
     * Delete sportsman
     * @param s 
     */ 
    void deleteSportsman(Sportsman s);

}
