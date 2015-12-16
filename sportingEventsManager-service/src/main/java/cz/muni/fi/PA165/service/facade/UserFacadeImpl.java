package cz.muni.fi.PA165.service.facade;

import cz.muni.fi.PA165.dto.CreateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import cz.muni.fi.PA165.dto.UserAuthenticateDTO;
import cz.muni.fi.PA165.dto.UserDTO;
import cz.muni.fi.PA165.dto.facade.UserFacade;
import cz.muni.fi.PA165.entity.Usr;
import cz.muni.fi.PA165.service.UserService;
import cz.muni.fi.PA165.service.BeanMappingService;


/**
 * 
 * @author jbouska
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public UserDTO findUserById(Long id) {
        Usr user = userService.findById(id);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

   
    
    @Override
    public UserDTO findUserByEmail(String email) {
        Usr user = userService.findUserByEmail(email);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }
    @Override
    public void updateUser(UserDTO s){
         Usr sp = userService.findById(s.getId());
         sp.setName(s.getName());
         sp.setSurname(s.getSurname());
         userService.updateSportsman(sp);
     }


    @Override
    public long registerUser(CreateUserDTO s) {
        
       Usr sp = new Usr();
       sp.setName(s.getName());
       sp.setSurname(s.getSurname());
       sp.setEmail(s.getEmail());
        userService.registerSportsman(sp, s.getPassword());
       s.setId(sp.getId());
        return sp.getId();
    }

    @Override
    public Collection<UserDTO> getAllUsers() {
        return beanMappingService.mapTo(userService.getAllSportsmans(), UserDTO.class);
    }

    @Override
    public boolean authenticate(UserAuthenticateDTO s) {
        return userService.authenticate(
                userService.findUserByEmail(s.getEmail()), s.getPassword());
    }

}
