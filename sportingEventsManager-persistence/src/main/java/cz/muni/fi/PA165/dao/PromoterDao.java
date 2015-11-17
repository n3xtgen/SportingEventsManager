package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Promoter;

import java.util.List;

/**
 * @author N3xtGeN
 */
public interface PromoterDao {

    /**
     * Vytvori promotera.
     *
     * @param promoter Promoter
     */
    public void create(Promoter promoter);

    /**
     * Smaze promotera.
     *
     * @param promoter Promoter
     */
    public void delete(Promoter promoter);

    /**
     * Updatuje promotera.
     *
     * @param promoter Promoter
     * @return Promoter
     */
    public Promoter update(Promoter promoter);

    /**
     * Vypise vsechny promotery.
     *
     * @return Vsichni promoteri
     */
    public List<Promoter> findAll();

    /**
     * Najde promotera pomoci id.
     *
     * @param id Id promotera k vyhledani
     * @return Promoter s danym id
     */
    public Promoter findById(Long id);

    /**
     * Najde promotera pomoci prijmeni.
     *
     * @param surname Prijmeni promotera k vyhledani
     * @return Promoter s danym prijmenim
     */
    public Promoter findBySurname(String surname);

    /**
     * Najde promotera pomoci rodneho cisla.
     *
     * @param citizenIdNumber Rodne cislo
     * @return Promoter se zadanym rodnym cislem
     *
     */
    public Promoter findByCitizenIdNumber(String citizenIdNumber);
}
