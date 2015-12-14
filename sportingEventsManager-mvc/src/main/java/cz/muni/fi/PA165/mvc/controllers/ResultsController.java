package cz.muni.fi.PA165.mvc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Eli on 14.12.2015.
 */

@Controller
@RequestMapping("/result")
public class ResultsController {

    final static Logger log = LoggerFactory.getLogger(ResultsController.class);

    @RequestMapping(value = "/show_resultForm", method = RequestMethod.GET)
    public String addResult(Model model){

        log.debug("addResult()");
        return "result/resultForm";
    }

    @RequestMapping(value = "/create_result", method = RequestMethod.GET)
    public String createResult(Model model){

        log.debug("addResult()");
        return "result/results";
    }
}
