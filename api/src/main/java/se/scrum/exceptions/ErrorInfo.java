package se.scrum.exceptions;

import javax.servlet.http.HttpServletRequest;

/**
 * Container that is returned to client when a exceptions is thrown
 * 
 * @author fidde
 *
 */
public class ErrorInfo {

    public final String url;
    public final String exception;

    /**
     * Constructor
     * 
     * @param url
     *            the {@link HttpServletRequest} url
     * @param ex
     *            the {@link Exception} that was thrown
     */
    public ErrorInfo(String url, Exception ex) {
        super();
        this.url = url;
        this.exception = ex.getMessage();
    }

    /**
     * Constructor
     * 
     * @param url
     *            the {@link HttpServletRequest} url
     * @param string
     *            the message
     */
    public ErrorInfo(String url, String string) {
        this.url = url;
        exception = string;
    }

}
