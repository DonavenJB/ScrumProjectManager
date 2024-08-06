package se.scrum.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Exception handler. Catches Controller exceptions and returns json
 * responses.
 * 
 * @author fidde
 *
 */
@ControllerAdvice
@RestController
public class ControllerExceptionHandler {

    private Logger log = Logger.getLogger(ControllerExceptionHandler.class);

    /**
     * Handles {@link NullPointerException}
     * 
     * @param request
     * @param exception
     * @return {@link ErrorInfo}
     */
    @ExceptionHandler(value = { NullPointerException.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorInfo notFound(HttpServletRequest request, Exception exception) {

        return returnError(request, exception);
    }

    /**
     * Handles {@link Exception}. Usualy when validation fails
     * 
     * @param request
     * @param exception
     * @return {@link ErrorInfo}
     */
    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorInfo invalid(HttpServletRequest request, Exception exception) {

        return returnError(request, exception);
    }

    /**
     * Handles unauthorized requests
     * 
     * @param request
     * @param exception
     * @return {@link ErrorInfo}
     */
    @ExceptionHandler(value = { AccessDeniedException.class })
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorInfo accessDenied(HttpServletRequest request,
            Exception exception) {

        return returnError(request, exception);
    }

    /**
     * Creates {@link ErrorInfo}
     * 
     * @param request
     * @param exception
     * @return {@link ErrorInfo}
     */
    private ErrorInfo returnError(HttpServletRequest request,
            Exception exception) {

        log.debug(exception.getMessage());
        return new ErrorInfo(request.getRequestURL().toString(), exception);
    }
}
