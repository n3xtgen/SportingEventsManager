package cz.muni.fi.PA165.rest.controllers;

import cz.muni.fi.PA165.rest.ApiUris;
import com.fasterxml.jackson.core.JsonProcessingException;
import cz.muni.fi.PA165.dto.CreateUserDTO;
import cz.muni.fi.PA165.dto.UserDTO;
import cz.muni.fi.PA165.dto.facade.UserFacade;
import cz.muni.fi.PA165.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.PA165.rest.exceptions.ResourceNotFoundException;

import java.util.Collection;

import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jbouska
 *
 */
@RestController
@RequestMapping(ApiUris.USERS_URI)
public class UsersController {

    final static Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Inject
    private UserFacade userFacade;

    /**
     * returns all users
     *
     * @return list of users
     * @throws JsonProcessingException
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<UserDTO> getUsers() throws JsonProcessingException {

        logger.debug("rest getUsers()");
        return userFacade.getAllUsers();
    }

    /**
     *
     * getting user according to id
     *
     * @param id user identifier
     * @return user
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUser(@PathVariable("id") long id) throws Exception {

        logger.debug("rest getUser({})", id);
        UserDTO userDTO = userFacade.findUserById(id);
        if (userDTO == null) {
            throw new ResourceNotFoundException();
        }
        return userDTO;

    }

    /**
     *
     * create user according to id
     *
     * @param user user to create
     * @return user
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO createUser(@RequestBody CreateUserDTO user) throws Exception {

        logger.debug("rest createUser()");

        try {
            Long id = userFacade.registerUser(user);
            return userFacade.findUserById(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }
}
