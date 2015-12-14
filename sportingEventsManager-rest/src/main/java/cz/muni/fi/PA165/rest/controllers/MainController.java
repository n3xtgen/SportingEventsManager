package cz.muni.fi.PA165.rest.controllers;

/**
 * @author jbouska
 */
import cz.muni.fi.PA165.rest.ApiUris;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    final static Logger logger = LoggerFactory.getLogger(MainController.class);

    /**
     *
     * @return rest uris
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {

        Map<String, String> resourcesMap = new HashMap<>();

        resourcesMap.put("users_uri", ApiUris.MAIN_URI + ApiUris.USERS_URI);

        return Collections.unmodifiableMap(resourcesMap);

    }
}
