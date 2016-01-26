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
        Object target = binder.getTarget();
        if(target instanceof SportDTO) {
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
        // Pred zobrazenim vysledky seradim podle position ty co dokoncili, pote ty co nedokoncili podle jmeno a pak DNF podle jmena
        SportDTO sportDTO = sportFacade.findSportById(sportId);
        sportDTO.setEntries(sportDTO.getEntries().stream().sorted(new Comparator<EntryDTO> () {
                @Override
                public int compare(EntryDTO o1, EntryDTO o2) {
                    EntryDTO.EntryState entryState1 = o1.getEntryState();
                    EntryDTO.EntryState entryState2 = o2.getEntryState();
                    
                    if (entryState1.ordinal() > entryState2.ordinal()) { // napr. pokud je jeden Finished a druhy Registered (zatim nedobehl), tak prvni bude pred nim
                        return -1;
                    } else if (entryState1.ordinal() < entryState2.ordinal()) {
                        return 1;
                    } else { // pokud maji stejnej stav
                        if (entryState1 == EntryDTO.EntryState.FINISHED) {
                            return o1.getPosition() - o2.getPosition(); // porovnam podle poradi
                        } else if ((entryState1 == EntryDTO.EntryState.REGISTERED) || (entryState1 == EntryDTO.EntryState.DISQUALIFIED)) {
                            return o1.getUsr().getSurname().compareTo(o2.getUsr().getSurname()); // seradim podle prijmeni
                        } else {
                            throw new IllegalStateException("Comparing entries with unknown state.");
                        }
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
