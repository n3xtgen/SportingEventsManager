package cz.muni.fi.PA165.mvc.controllers;

import cz.muni.fi.PA165.dto.facade.EventFacade;
import cz.muni.fi.PA165.dto.facade.SportFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jamik on 10.12.2015.
 */


@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventFacade eventFacade;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String eventsList(Model model){
        model.addAttribute("events", eventFacade.getAllEvents());
        return "event/list";
    }


}
