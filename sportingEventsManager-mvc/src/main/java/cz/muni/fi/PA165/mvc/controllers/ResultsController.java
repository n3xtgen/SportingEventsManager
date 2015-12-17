package cz.muni.fi.PA165.mvc.controllers;

import cz.muni.fi.PA165.dto.*;
import cz.muni.fi.PA165.dto.facade.EntryFacade;
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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by Eli on 14.12.2015.
 */

@Controller
@RequestMapping("/result")
public class ResultsController {


    @InitBinder
    protected void initBinder(WebDataBinder binder){
        if((binder.getTarget() instanceof CreateEntryDTO) || (binder.getTarget() instanceof EntryDTO)) {
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

    final static Logger log = LoggerFactory.getLogger(ResultsController.class);


    @RequestMapping(value = "/show/{entryId}", method = RequestMethod.GET)
    public String updateResult(@PathVariable("entryId") Long entryId,  Model model){
        model.addAttribute("resultForm", entryFacade.findEntryById(entryId));
        log.debug("updateResult()");
        return "result/resultForm";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String createResult(@ModelAttribute("resultForm") @Valid EntryDTO formBean, BindingResult bResult,
                               RedirectAttributes redirectAttributes, Model model){

        if(bResult.hasErrors()) {

            for(Iterator<ObjectError> itr = bResult.getAllErrors().iterator(); itr.hasNext();)
            {
                log.debug("createResult --->" + itr.next().toString());
            }

            log.debug("createResult - has errors");
            redirectAttributes.addFlashAttribute("alert_danger", "System was not able to change results");
            return "result/resultForm";
        }

        log.debug("--> createResult ----> " + formBean.toString() + " || " + formBean.getSport().toString() + " || " + formBean.getUsr().toString());

        entryFacade.updateEntry(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Result " + formBean.getUsr().getName() + " " + formBean.getUsr().getSurname() + " was updated successfully");

        log.debug("createResult() - success");
   
        return "redirect:/event/list";
    }
}
