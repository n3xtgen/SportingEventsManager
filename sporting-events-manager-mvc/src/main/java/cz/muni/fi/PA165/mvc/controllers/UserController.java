/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.mvc.controllers;

import cz.muni.fi.PA165.dto.CreateSportsmanDTO;
import cz.muni.fi.PA165.dto.SportsmanAuthenticateDTO;
import cz.muni.fi.PA165.dto.SportsmanDTO;
import cz.muni.fi.PA165.dto.facade.SportsmanFacade;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    private SportsmanFacade userFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userFacade.getAllSportsmans());
        return "user/list";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newSportsman(Model model) {
        log.debug("new()");
        model.addAttribute("sportsmanCreate", new CreateSportsmanDTO());
        return "user/new";
    }
    
     @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detailSportsman(@PathVariable long id ,Model model) {
        log.debug("detail()");
        model.addAttribute("sportsmanDetail", userFacade.findSportsmanById(id));
        return "user/detail";
    }
    
    

    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("sportsmanCreate") CreateSportsmanDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        log.debug("create(sportsmanCreate={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
          
            return "user/new";
        }
        //create sportsman
       
        Long id = userFacade.registerSportsman(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "User was created");
       request.getSession().setAttribute("authenticatedUser", userFacade.findSportsmanById(id));
         return "redirect:" + uriBuilder.path("/user/detail/{id}").buildAndExpand(id).encode().toUriString();
    }
    
   
     @RequestMapping(value = "/login_page", method = RequestMethod.GET)
    public String logingGet(Model model) {
         log.debug("login");
        model.addAttribute("authenticateSportsman", new SportsmanAuthenticateDTO());
        return "user/login_page";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@Valid @ModelAttribute("authenticateSportsman") SportsmanAuthenticateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        log.debug("login(sportsmanLogin={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            
            return "user/login_page";
        }
        //create sportsman
       
       if(!userFacade.authenticate(formBean))
       {
            model.addAttribute("alert_warning", "authentication fail");
             return "user/login_page";
       }
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "loged in");
       request.getSession().setAttribute("authenticatedUser", userFacade.findSportsmanByEmail(formBean.getEmail()));
        return "redirect:/";
    }
    
    
     @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request) {
         log.debug("logout");
         request.getSession().removeAttribute("authenticatedUser");
         model.addAttribute("alert_success", "loged out");
        return "/home";
    }
}
