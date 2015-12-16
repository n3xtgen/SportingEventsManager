package cz.muni.fi.PA165.rest.assemblers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

import cz.muni.fi.PA165.dto.UserDTO;

/**
 *
 * @author jbouska
 */
@Component
public class UserResourceAssembler implements ResourceAssembler<UserDTO, Resource<UserDTO>> {

    @Override
    public Resource<UserDTO> toResource(UserDTO userDTO) {
        long id = userDTO.getId();
        Resource<UserDTO> productResource = new Resource<UserDTO>(userDTO);

        try {
            productResource.add(linkTo(UserDTO.class).slash(userDTO.getId()).withSelfRel());
         

        } catch (Exception ex) {
            Logger.getLogger(UserResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource", ex);
        }

        return productResource;
    }
}
