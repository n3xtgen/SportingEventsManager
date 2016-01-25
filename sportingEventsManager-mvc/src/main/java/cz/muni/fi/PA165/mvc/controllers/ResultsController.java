package cz.muni.fi.PA165.mvc.controllers;

import cz.muni.fi.PA165.dto.*;
import cz.muni.fi.PA165.dto.facade.EntryFacade;
import cz.muni.fi.PA165.dto.facade.SportFacade;
import cz.muni.fi.PA165.mvc.propertyEditors.SportDTOPropertyEditor;
import cz.muni.fi.PA165.mvc.propertyEditors.UserDTOPropertyEditor;
import cz.muni.fi.PA165.mvc.validators.ResultFormValidator;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

/**
 * @author n3xtgen
 */
@Controller
@RequestMapping("/result")
public class ResultsController {

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        if((binder.getTarget() instanceof EntryDTO)) {
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
    public String showResults(@PathVariable("sportId") long sportId, Model model, HttpServletRequest request)
    {
        // Pred zobrazenim vysledky seradim podle position a nebo jmeno pokud position je 0, coz znaci ze s timto Entry jeste vysledek nebyl ukladan a je novy
        SportDTO sportDTO = sportFacade.findSportById(sportId);
        sportDTO.setEntries(sportDTO.getEntries().stream().sorted(new Comparator<EntryDTO> () {
                @Override
                public int compare(EntryDTO o1, EntryDTO o2) {
                    int entryPosition1 = o1.getPosition();
                    int entryPosition2 = o2.getPosition();
                    
                    if ((entryPosition1 == 0) && (entryPosition2 == 0)) { // oba jeste nemaji vysledek, porovnavam podle prijmeni
                        return o1.getUsr().getSurname().compareTo(o2.getUsr().getSurname());
                    } else if ((entryPosition1 > 0) && (entryPosition2 <= 0)) { // prvni uz ma zapsan nejakou pozici, druhy je novy
                        return 1;
                    } else if ((entryPosition1 <= 0) && (entryPosition2 > 0)) { // prvni je novy, druhy uz ma zapsan nejakou pozici
                        return -1;
                    } else { // oba maji zapsanou pozici - porovnavam podle ni..
                        return (entryPosition1 - entryPosition2);
                    }
                }
            }).collect(Collectors.toList()));
        
        model.addAttribute("sport", sportDTO);
        model.addAttribute("signedUser", request.getSession().getAttribute("authenticatedUser"));

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
                                BindingResult bResult, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request)
    {
        if(bResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("alert_danger", "You have entered wrong time format.");
            return "redirect:/result/{sportId}";
        }
        if(!((UserDTO) request.getSession().getAttribute("authenticatedUser")).isAdmin()) {
            redirectAttributes.addFlashAttribute("alert_danger", "Tou cannot change results because you are not admin.");
            return "redirect:/result/{sportId}";
        }
        
        sportFacade.updateSportResults(sport);
        
        redirectAttributes.addFlashAttribute("alert_success", "Result has been successfully updated.");

        return "redirect:/result/{sportId}";
    }
}
