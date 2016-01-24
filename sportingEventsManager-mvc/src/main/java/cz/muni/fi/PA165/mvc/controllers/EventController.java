package cz.muni.fi.PA165.mvc.controllers;

import cz.muni.fi.PA165.dto.*;
import cz.muni.fi.PA165.dto.facade.EntryFacade;
import cz.muni.fi.PA165.dto.facade.EventFacade;
import cz.muni.fi.PA165.dto.facade.SportFacade;
import cz.muni.fi.PA165.mvc.propertyEditors.EventDTOPropertyEditor;
import cz.muni.fi.PA165.mvc.validators.EventFormValidator;
import cz.muni.fi.PA165.mvc.validators.SportFormValidator;
import cz.muni.fi.PA165.service.constant.SLayerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jamik on 10.12.2015.
 */

@Controller
@RequestMapping("/event")
public class EventController {

    final static Logger log = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventFacade eventFacade;

    @Autowired
    private EntryFacade entryFacade;

    @Autowired
    private SportFacade sportFacade;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        if((binder.getTarget() instanceof CreateEventDTO) || (binder.getTarget() instanceof EventDTO)) {
            // we need to convert String to Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            binder.registerCustomEditor(Date.class, "startDate", new CustomDateEditor(dateFormat, false));
            binder.registerCustomEditor(Date.class, "endDate", new CustomDateEditor(dateFormat, false));

            binder.addValidators(new EventFormValidator());
        }

        if((binder.getTarget() instanceof CreateSportDTO) || (binder.getTarget() instanceof SportDTO)){
            // we need to convert String to Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            binder.registerCustomEditor(Date.class, "startDate", new CustomDateEditor(dateFormat, false));
            binder.registerCustomEditor(Date.class, "endDate", new CustomDateEditor(dateFormat, false));
            binder.registerCustomEditor(EventDTO.class, new EventDTOPropertyEditor());
            binder.addValidators(new SportFormValidator());
        }


    }

    /**
     * Show list of events
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String eventsList(Model model, HttpServletRequest request){

        model.addAttribute("events", eventFacade.getAllEvents()); // pass there all events
        model.addAttribute("signedUser", request.getSession().getAttribute("authenticatedUser")); // we also need to pass there the user
        log.debug("eventList()");
        return "event/list";
    }

    /**
     * Delete event and redirects back to list of events
     * @param deleteId
     * @param redirectAttributes
     * @param uriComponentsBuilder
     * @return
     */
    @RequestMapping(value = "/delete/{deleteId}", method = RequestMethod.POST)
    public String deleteEvent(@PathVariable("deleteId") long deleteId, RedirectAttributes redirectAttributes,
                              UriComponentsBuilder uriComponentsBuilder){

        EventDTO event = eventFacade.findEventById(deleteId);
        // events that are in progress cant be removed
        if(eventFacade.deleteEvent(deleteId))
            redirectAttributes.addFlashAttribute("alert_success", "Event \"" + event.getName() + "\" has been removed.");
        else
            redirectAttributes.addFlashAttribute("alert_danger", "You cant remove event \"" + event.getName() + "\" because it´s currently in progress.");

        log.debug("deleteEvent()");
        return "redirect:/event/list";
    }

    /**
     * Shows up new event from
     * @param model
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addEvent(Model model){
        model.addAttribute("eventForm", new CreateEventDTO());
        log.debug("addEvent()");
        return "event/eventForm";
    }

    /**
     * Shows up event update form
     * @param updateId
     * @param model
     * @return
     */
    @RequestMapping(value="/update/{updateId}", method = RequestMethod.GET)
    public String updateEvent(@PathVariable("updateId") long updateId, Model model){
        model.addAttribute("eventForm", eventFacade.findEventById(updateId));
        log.debug("updateEvent");
        return "event/eventForm";
    }

    /**
     * Hooked onto create form submit
     * @param formBean
     * @param bResult
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="/create", method= RequestMethod.POST)
    public String create(@ModelAttribute("eventForm") @Valid CreateEventDTO formBean, BindingResult bResult,
                         Model model, RedirectAttributes redirectAttributes){

        // if we have some errors, lets stay on the eventForm page
        if(bResult.hasErrors())
            return "event/eventForm";

        // save the new event
        eventFacade.addEvent(formBean);
        // show success alert
        redirectAttributes.addFlashAttribute("alert_success", "Event \"" + formBean.getName() + "\" has been created.");

        // go back to list of events
        return "redirect:/event/list";
    }

    /**
     * Update the event in database
     * @param formBean
     * @param bResult
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="/update", method= RequestMethod.POST)
    public String update(@ModelAttribute("eventForm") @Valid EventDTO formBean, BindingResult bResult,
                         Model model, RedirectAttributes redirectAttributes){

        // if we have any errors, lets stay on the eventForm page
        if(bResult.hasErrors())
            return "event/eventForm";
        // update event
        eventFacade.updateEvent(formBean);
        // show success alert
        redirectAttributes.addFlashAttribute("alert_success", "Event \"" + formBean.getName() + "\" has been updated.");

        // go back to list of events
        return "redirect:/event/list";
    }

    /**
     * Sign up for a specified sport
     * @param sportId
     * @param request
     * @param redirectAttributes
     * @param model
     * @return
     */
    @RequestMapping(value="/signIn/{sportId}", method = RequestMethod.POST)
    public String signUp(@PathVariable("sportId") long sportId, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model){
        log.debug("signUp()");
        UserDTO sportsman = (UserDTO)request.getSession().getAttribute("authenticatedUser");
        // something went wrong
        if(sportsman == null){
            log.debug("signUp() -> failure");
            redirectAttributes.addFlashAttribute("alert_danger", "System was not able to register you.");
            return "redirect:/event/list";
        }

        // create new registration
        CreateEntryDTO entry = new CreateEntryDTO();
        entry.setSportId(sportId);
        entry.setSportsmanId(sportsman.getId());
        // man can only sign up to a sport before it starts
        Long retVal = entryFacade.registerEntry(entry);
        if(retVal >= SLayerConstants.NO_ERROR)
            redirectAttributes.addFlashAttribute("alert_success", "You have signed up to a sport.");
        else if(retVal == SLayerConstants.ERROR_SPORT_ALREADY_STARTED)
            redirectAttributes.addFlashAttribute("alert_danger", "You can´t register to this sport any more.");
        else if(retVal == SLayerConstants.ERROR_TIME_CONFLICT)
            redirectAttributes.addFlashAttribute("alert_danger", "You can´t register to this sport because it would collide with one of your sports.");

        return "redirect:/event/list";
    }

    /**
     * Sigh out from specified sport
     * @param sportId
     * @param request
     * @param redirectAttributes
     * @param model
     * @return
     */
    @RequestMapping(value="signOut/{sportId}", method= RequestMethod.POST)
    public String signOut(@PathVariable("sportId") long sportId, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model){
        UserDTO sportsman = (UserDTO)request.getSession().getAttribute("authenticatedUser");
        // something went wrong
        if(sportsman == null){
            log.debug("signOut() -> failure");
            redirectAttributes.addFlashAttribute("alert_danger", "System was not able to unregister you.");
            return "redirect:/event/list";
        }

        // find the registration and delete it
        EntryDTO entry = entryFacade.findEntryBySportsmanAndSportId(sportId, sportsman.getId());
        if(entry != null){
            if(entryFacade.deleteEntry(entry.getIdEntry()))
                redirectAttributes.addFlashAttribute("alert_success", "You have signed out from a sport.");
            else
                redirectAttributes.addFlashAttribute("alert_danger", "You can´t sign out from a sport after the start.");
        }

        return "redirect:/event/list";
    }

    /**
     * Create sport, invoked by pressing Add competition button
     * @param model
     * @param eventId
     * @return
     */
    @RequestMapping(value="/{eventId}/addSport", method = RequestMethod.GET)
    public String addSport(Model model, @PathVariable("eventId") long eventId){

        CreateSportDTO sport = new CreateSportDTO();
        sport.setEvent(eventFacade.findEventById(eventId)); // pass event to sport form
        model.addAttribute("sportForm", sport);

        return "event/sportForm";
    }

    /**
     * Create sport, invoked by submiting New sport form
     * @param formBean
     * @param bResult
     * @param request
     * @param redirectAttributes
     * @param model
     * @return
     */
    @RequestMapping(value = "/createSport", method = RequestMethod.POST)
    public String createSport(@ModelAttribute("sportForm") @Valid CreateSportDTO formBean, BindingResult bResult,
                               HttpServletRequest request, RedirectAttributes redirectAttributes, Model model){

       if(bResult.hasErrors()) {
           redirectAttributes.addFlashAttribute("alert_danger", "System was not able to create new sport.");
           return "event/sportForm";
        }
        log.debug("createSport - " + (formBean.getStartTime() == null ? "null " : formBean.getStartTime().toString()));
        // add create new sport
        sportFacade.addNewSport(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Sport \"" + formBean.getName() + "\" has been created.");

        log.debug("createSport()");
        return "redirect:/event/list";
    }

    /**
     * delete sport, invoked by pressing delete button belonging to the sport
     * @param sportId
     * @param eventId
     * @param redirectAttributes
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteSport/{sportId}/{eventId}", method = RequestMethod.POST)
    public String deleteSport(@PathVariable("sportId") long sportId, @PathVariable("eventId") long eventId,
                              RedirectAttributes redirectAttributes, Model model){

        SportDTO delSport = sportFacade.findSportById(sportId);
        EventDTO event = eventFacade.findEventById(eventId);
        // remove the sport from event, but only if it´s not currently in progress
        if(eventFacade.removeSport(eventId, sportId))
            redirectAttributes.addFlashAttribute("alert_success", "Sport \"" + delSport.getName() + "\" has been removed from event \"" + event.getName() + "\".");
        else
            redirectAttributes.addFlashAttribute("alert_danger", "You can´t remove \"" + delSport.getName() + "\" because it´s currently in progress.");
        log.debug("deleteSport()");
        return "redirect:/event/list";
    }

}
