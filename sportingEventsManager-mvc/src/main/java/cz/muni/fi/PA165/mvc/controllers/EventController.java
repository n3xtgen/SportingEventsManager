package cz.muni.fi.PA165.mvc.controllers;

import cz.muni.fi.PA165.dto.EventDTO;
import cz.muni.fi.PA165.dto.CreateEventDTO;
import cz.muni.fi.PA165.dto.facade.EventFacade;
import cz.muni.fi.PA165.mvc.validators.EventFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

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


    @InitBinder
    protected void initBinder(WebDataBinder binder){
        if(binder.getTarget() instanceof CreateEventDTO) {
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
        model.addAttribute("newEvent", new CreateEventDTO());
        return "event/new";
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
    public String create(@ModelAttribute("newEvent") @Valid CreateEventDTO formBean, BindingResult bResult,
                         Model model, RedirectAttributes redirectAttributes){

        System.out.println("public String create(@Validated @ModelAttribute()");
        log.debug("asdasd", "public String create(@Validated @ModelAttribute(");
        log.info("info", "public String create(@Validated @ModelAttribute(");
        // if we have some errors, lets stay on the new event page
        if(bResult.hasErrors())
            return "event/new";

        // save the new event
        eventFacade.addEvent(formBean);
        // show success alert
        redirectAttributes.addFlashAttribute("alert_success", "Event " + formBean.getName() + " was created successfully");

        // go back to list of events
        return "redirect:/event/list";
    }

    /**
     * Shows up event update form
     * @param updateId
     * @param model
     * @return
     */
    @RequestMapping(value="/update/{updateId}", method = RequestMethod.GET)
    public String updateEvent(@PathVariable("updateId") long updateId, Model model){
        model.addAttribute("updateEvent", eventFacade.findEventById(updateId));
        return "event/update";
    }

    // TODO: implement
    @RequestMapping(value="/signUp/sportsman={sportsmanId}/event={eventId}/sportId={sportId}", method = RequestMethod.POST)
    public String signUp(@PathVariable("sportsmanId") long sportsmanId, @PathVariable("eventId") long eventId,
                         @PathVariable("sportId") long sportId){

        return "redirect:/event/list";
    }
}
