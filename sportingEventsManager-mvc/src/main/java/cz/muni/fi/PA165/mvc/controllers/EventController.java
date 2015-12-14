package cz.muni.fi.PA165.mvc.controllers;

import cz.muni.fi.PA165.dto.*;
import cz.muni.fi.PA165.dto.facade.EntryFacade;
import cz.muni.fi.PA165.dto.facade.EventFacade;
import cz.muni.fi.PA165.dto.facade.SportFacade;
import cz.muni.fi.PA165.mvc.validators.EventFormValidator;
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            binder.registerCustomEditor(Date.class, "startDate", new CustomDateEditor(dateFormat, false));
            binder.registerCustomEditor(Date.class, "endDate", new CustomDateEditor(dateFormat, false));

            binder.addValidators(new EventFormValidator());
        }
    }

    /**
     * Show list of events
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String eventsList(Model model){
        model.addAttribute("events", eventFacade.getAllEvents());

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
        eventFacade.deleteEvent(deleteId);
        redirectAttributes.addFlashAttribute("alert_success", "Event \"" + event.getName() + "\" was removed");
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
        redirectAttributes.addFlashAttribute("alert_success", "Event \"" + formBean.getName() + "\" was created successfully");

        // go back to list of events
        return "redirect:/event/list";
    }

    @RequestMapping(value="/update", method= RequestMethod.POST)
    public String update(@ModelAttribute("eventForm") @Valid EventDTO formBean, BindingResult bResult,
                         Model model, RedirectAttributes redirectAttributes){

        // if we have any errors, lets stay on the eventForm page
        if(bResult.hasErrors())
            return "event/eventForm";
        // update event
        eventFacade.updateEvent(formBean);
        // show success alert
        redirectAttributes.addFlashAttribute("alert_success", "Event " + formBean.getName() + " was updated successfully");

        // go back to list of events
        return "redirect:/event/list";
    }

    @RequestMapping(value="/signIn/{sportId}", method = RequestMethod.POST)
    public String signUp(@PathVariable("sportId") long sportId, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model){
        System.out.println("signUp()");
        log.debug("signUp()");
        SportsmanDTO sportsman = (SportsmanDTO)request.getSession().getAttribute("authenticatedUser");
        // something went wrong
        if(sportsman == null){
            log.debug("signUp() -> failure");
            redirectAttributes.addFlashAttribute("alert_danger", "System was not able to register you");
            return "redirect:/event/list";
        }

        // TODO: it might be good to include some limitations into Sport (for ex. max sportsman per sport)
        // create new registration
        CreateEntryDTO entry = new CreateEntryDTO();
        entry.setSportId(sportId);
        entry.setSportsmanId(sportsman.getIdSportsman());
        entryFacade.registerEntry(entry);

        redirectAttributes.addFlashAttribute("alert_success", "You have successfully signed up");

        return "redirect:/event/list";
    }

    @RequestMapping(value="", method= RequestMethod.POST)
    public String signOut(@PathVariable("sportId") long sportId, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model){
        SportsmanDTO sportsman = (SportsmanDTO)request.getSession().getAttribute("authenticatedUser");
        // something went wrong
        if(sportsman == null){
            log.debug("signOut() -> failure");
            redirectAttributes.addFlashAttribute("alert_danger", "System was not able to unregister you");
            return "redirect:/event/list";
        }

        
        //entryFacade.deleteEntry(/* find entry by sport+sportsman */);

        redirectAttributes.addFlashAttribute("alert_success", "You have successfully signed out");

        return "redirect:/event/list";
    }

    @RequestMapping(value="/results/{sportId}", method = RequestMethod.GET)
    public String showResults(@PathVariable("sportId") long sportId, Model model){
        log.debug("showResults()");
        model.addAttribute("entries", entryFacade.findEntriesBySportId(sportId));
        model.addAttribute("sport", sportFacade.findSportById(sportId));

        return "result/results";
    }
}
