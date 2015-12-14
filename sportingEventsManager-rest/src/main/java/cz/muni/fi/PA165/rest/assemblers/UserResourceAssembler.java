package cz.muni.fi.PA165.rest.assemblers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

import cz.muni.fi.PA165.dto.SportsmanDTO;

/**
 *
 * @author jbouska
 */
@Component
public class UserResourceAssembler implements ResourceAssembler<SportsmanDTO, Resource<SportsmanDTO>> {

    @Override
    public Resource<SportsmanDTO> toResource(SportsmanDTO sportsmanDTO) {
        long id = sportsmanDTO.getIdSportsman();
        Resource<SportsmanDTO> productResource = new Resource<SportsmanDTO>(sportsmanDTO);

        try {
            productResource.add(linkTo(SportsmanDTO.class).slash(sportsmanDTO.getIdSportsman()).withSelfRel());
         

        } catch (Exception ex) {
            Logger.getLogger(UserResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource", ex);
        }

        return productResource;
    }
}
