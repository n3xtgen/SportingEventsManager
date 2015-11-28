package cz.muni.fi.PA165.dto.facade;

import cz.muni.fi.PA165.dto.CreateEntryDTO;
import cz.muni.fi.PA165.dto.EntryDTO;

import java.util.Collection;

/**
 * @author n3xtgen
 */
public interface EntryFacade {

    /**
     * Vytvori novou registraci mezi zavodem a sportovcem.
     *
     * @param entryDto
     */
    void registerEntry(CreateEntryDTO entryDto);

    /**
     * Upravi registraci podle pozadavku.
     *
     * @param entryDto
     */
    void updateEntry(EntryDTO entryDto);

    /**
     * Smaze registraci podle Id.
     *
     * @param id
     */
    void deleteEntry(Long id);


    /**
     * Najde registraci podle Id.
     *
     * @param id
     * @return
     */
    EntryDTO findEntryById(Long id);

    /**
     * Vrati seznam registraci podle Sportu (zavodu).
     *
     * @return
     */
    Collection<EntryDTO> findEntriesBySportId(Long sportId);

    /**
     * Vrati seznam registraci podle sportovce.
     *
     * @return
     */
    Collection<EntryDTO> findEntriesBySportsmanId(Long sportsmanId);

}
