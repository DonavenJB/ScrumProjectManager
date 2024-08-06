package se.scrum.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
    private static final String LOG_LABEL = "[ApiController]";

    public static final String API = "/api";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public RepresentationModel<?> home() {
        logger.info("{} Handling home request", LOG_LABEL);
        RepresentationModel<?> resourceSupport = new RepresentationModel<>();
        Link apiLink = Link.of("/api").withRel("api");
        resourceSupport.add(apiLink);
        logger.info("{} Home request handled successfully", LOG_LABEL);
        return resourceSupport;
    }

    @RequestMapping(value = API, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public RepresentationModel<?> getApiUsage() {
        logger.info("{} Handling getApiUsage request", LOG_LABEL);
        RepresentationModel<?> resourceSupport = new RepresentationModel<>();
        Link usersLink = Link.of("/users").withRel("users");
        resourceSupport.add(usersLink);
        logger.info("{} getApiUsage request handled successfully", LOG_LABEL);
        return resourceSupport;
    }
}
