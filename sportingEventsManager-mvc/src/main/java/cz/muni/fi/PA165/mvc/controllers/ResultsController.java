package cz.muni.fi.PA165.mvc.controllers;

import cz.muni.fi.PA165.dto.*;
import cz.muni.fi.PA165.dto.facade.EntryFacade;
import cz.muni.fi.PA165.dto.facade.SportFacade;
import cz.muni.fi.PA165.mvc.propertyEditors.SportDTOPropertyEditor;
import cz.muni.fi.PA165.mvc.propertyEditors.UserDTOPropertyEditor;
import cz.muni.fi.PA165.mvc.validators.ResultFormValidator;
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

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 * @author n3xtgen
 */
@Controller
@RequestMapping("/result")
@SessionAttributes("sport")
public class ResultsController {

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        if((binder.getTarget() instanceof EntryDTO)) {
            // we need to convert String to Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            binder.registerCustomEditor(Date.class, "time", new CustomDateEditor(dateFormat, false));
            // convert String to SportDTO
            binder.registerCustomEditor(SportDTO.class, new SportDTOPropertyEditor());
            // convert String to UsrDTO
            binder.registerCustomEditor(UserDTO.class, new UserDTOPropertyEditor());

            binder.addValidators(new ResultFormValidator());
        }
    }

    @Autowired
    private EntryFacade entryFacade;
    
    @Autowired
    private SportFacade sportFacade;

    final static Logger log = LoggerFactory.getLogger(ResultsController.class);
    
    /**
     * Zobrazi vysledky daneho sportu.
     * 
     * @param sportId
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/{sportId}", method = RequestMethod.GET)
    public String showResults(@PathVariable("sportId") long sportId, Model model, HttpServletRequest request){
        
        model.addAttribute("signedUser", request.getSession().getAttribute("authenticatedUser"));
        model.addAttribute("sport", sportFacade.findSportById(sportId));

        return "result/sport";
    }
    
    /**
     * Aktualizuje vysledky sportu.
     * 
     * @param sportId
     * @param sport
     * @param bResult
     * @param redirectAttributes
     * @param model
     * @return 
     */
    @RequestMapping(value = "/{sportId}/update", method = RequestMethod.POST)
    public String updateResults(@PathVariable("sportId") long sportId, @ModelAttribute("sportResultsForm") @Valid SportDTO sport,
                                BindingResult bResult, RedirectAttributes redirectAttributes, Model model) {

        if(bResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("alert_danger", "You have entered wrong time format");
            return "redirect:/result/{sportId}";
        }
        
        sportFacade.updateSportResults(sport);
        
        redirectAttributes.addFlashAttribute("alert_success", "Result has been successfully updated.");

        return "redirect:/result/{sportId}";
    }
}
