package cz.muni.fi.PA165.service;

import java.util.List;

import cz.muni.fi.PA165.entity.Usr;

/**
 *
 * @author jbouska
 */
public interface UserService {

    /**
     * Register new User.
     *
     * @param u sportsman to register
     * @param unencryptedPassword
     */
    void registerSportsman(Usr u, String unencryptedPassword);

    /**
     * Get all sportsmans.
     *
     * @return list of sportsmans
     */
    List<Usr> getAllSportsmans();

    /**
     * Authenticate sportsman.
     *
     * @param u
     * @param password
     * @return true if is authenticate
     */
    boolean authenticate(Usr u, String password);

    /**
     * Find sportsman by ID.
     *
     * @param sportsmanId
     * @return
     */
    Usr findById(Long sportsmanId);

    /**
     * Find sportsman by surname. 
     * 
     * @param surname
     * @return
     */
    List<Usr> findUserBySurname(String surname);

    /**
     * Find sportsman by email
     * 
     * @param email
     * @return 
     */
    Usr findUserByEmail(String email);

    /**
     * Update sportsman.
     * @param s 
     */
    void updateSportsman(Usr s);
    
    /**
     * Delete sportsman
     * @param s 
     */ 
    void deleteSportsman(Usr s);

}
