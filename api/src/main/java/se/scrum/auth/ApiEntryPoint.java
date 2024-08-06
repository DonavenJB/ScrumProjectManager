package se.scrum.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Api authentication entrypoint. Since a token is only given if a user posts
 * credentials to a specified url we always reject requests to other urls
 * 
 * @author fidde
 *
 */
public class ApiEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        response.sendError(HttpStatus.UNAUTHORIZED.value(),
                "Visit /api for help");
    }

}
