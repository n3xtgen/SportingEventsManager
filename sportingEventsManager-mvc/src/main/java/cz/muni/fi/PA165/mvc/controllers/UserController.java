/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.mvc.controllers;


import cz.muni.fi.PA165.dto.CreateUserDTO;
import cz.muni.fi.PA165.dto.UserAuthenticateDTO;
import cz.muni.fi.PA165.dto.UserDTO;
import cz.muni.fi.PA165.dto.facade.UserFacade;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author jbouska
 */
@Controller
@RequestMapping("/user")
public class UserController {

    final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserFacade userFacade;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userFacade.getAllUsers());
        return "user/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newSportsman(Model model) {
        log.debug("new()");
        model.addAttribute("userCreate", new CreateUserDTO());
        return "user/new";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detailSportsman(@PathVariable long id, Model model) {
        model.addAttribute("userDetail", userFacade.findUserById(id));
        log.info("je admin??" + userFacade.findUserById(id).isAdmin());
        return "user/detail";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateSportsman(@PathVariable long id, Model model) {
        log.debug("update()");
        model.addAttribute("userUpdate", userFacade.findUserById(id));
        return "user/update_page";
    }

 
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateSportsman(@PathVariable long id, @Valid @ModelAttribute("userUpdate") UserDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        log.debug("update(userUpdate={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {

            return "user/update_page";
        }

        UserDTO s = userFacade.findUserById(id);
        formBean.setEmail(s.getEmail());
        formBean.setId(s.getId());
        //update sportsman
        try {
            userFacade.updateUser(formBean);
        } catch (ConstraintViolationException e) {
            model.addAttribute("alert_warning", "Account with this email is already registered.");
            return "user/update_page";
        }
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "User has been updated.");
        return "redirect:" + uriBuilder.path("/user/detail/{id}").buildAndExpand(formBean.getId()).encode().toUriString();
    }

      
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid
            @ModelAttribute("userCreate") CreateUserDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, HttpServletRequest request
    ) {
        log.debug("create(userCreate={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {

            return "user/new";
        }
        //create sportsman
        long id;
        try {
           id = userFacade.registerUser(formBean);
        } catch (ConstraintViolationException e) {
            model.addAttribute("alert_warning", "Account with this email is already registered.");
            return "user/new";
        }
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "User has been created.");
        request.getSession().setAttribute("authenticatedUser", userFacade.findUserById(id));
        return "redirect:" + uriBuilder.path("/user/detail/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/login_page", method = RequestMethod.GET)
    public String logingGet(Model model
    ) {
        log.debug("login");
        model.addAttribute("authenticateUser", new UserAuthenticateDTO());
        return "user/login_page";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@Valid
            @ModelAttribute("authenticateUser") UserAuthenticateDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, HttpServletRequest request
    ) {
        log.debug("login(sportsmanLogin={})", formBean);
        
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            return "user/login_page";
        }
        
        //
        if (!userFacade.authenticate(formBean)) {
            model.addAttribute("alert_warning", "You couldn't be logged in - wrong email or password.");
            return "user/login_page";
        }
        
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "You have been logged in as " + formBean.getEmail() + ".");
        request.getSession().setAttribute("authenticatedUser", userFacade.findUserByEmail(formBean.getEmail()));
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request) {
        log.debug("logout");
        request.getSession().removeAttribute("authenticatedUser");
        model.addAttribute("alert_success", "You have been logged out.");
        return "/home";
    }
}
