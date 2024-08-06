package se.scrum.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import se.scrum.domain.user.User;
import se.scrum.repo.UserRepo;

/**
 * Implementation of {@link UserDetailsService}. The service provides
 * authentication by checking the db for users by their username.
 * 
 * @author fidde
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepo repo;

    /**
     * Constructor
     * 
     * @param repo
     *            {@link UserRepo}
     */
    @Autowired
    public UserDetailsServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User result = repo.findByName(username);
        if (result == null)
            throw new UsernameNotFoundException(username + " not found!");

        return new UserDetailsImpl(result);
    }

}
