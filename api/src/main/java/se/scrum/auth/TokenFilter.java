package se.scrum.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import se.scrum.domain.user.User;
import se.scrum.service.user.UserService;
import se.scrum.util.TokenUtil;

/**
 * Filter to validate requests to secured resources Each request should contain
 * a Token header.
 * 
 * @author fidde
 *
 */
public class TokenFilter extends GenericFilterBean {

    private UserService service;
    private Logger log = Logger.getLogger(TokenFilter.class);

    /**
     * Constructor
     * 
     * @param service
     *            {@link UserService}
     */
    @Autowired
    public TokenFilter(UserService service) {
        super();
        this.service = service;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String token = req.getHeader("Token");

        log.debug("token: " + token);
        if (token == null) {

            // if a user does not have a token but has been authenticated set it
            // to false
            setAuthToFalse();

        } else {

            log.debug("checking token: " + token);
            String name = TokenUtil.getUsernameFromToken(token);
            User userByName = service.getUserByName(name);

            if (userByName != null) {

                boolean validToken = TokenUtil.isValidToken(token, userByName);
                if (validToken) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userByName.getName(), userByName.getPassword());

                    SecurityContextHolder.getContext().setAuthentication(
                            authentication);
                    log.debug("token valid, setting auth: " + authentication);

                } else {

                    // set auth to false
                    setAuthToFalse();
                }
            }
        }

        chain.doFilter(req, res);
    }

    /**
     * Sets the current clients authentication to false in
     * {@link SecurityContextHolder}
     */
    private void setAuthToFalse() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication != null) {
            authentication.setAuthenticated(false);
        }
    }

}
