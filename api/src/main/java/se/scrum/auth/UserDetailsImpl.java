package se.scrum.auth;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import se.scrum.domain.user.User;

/**
 * Implementation of {@link UserDetails}. The service is used to authenticate
 * details for users.
 * 
 * @author fidde
 *
 */
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = -173897570644326108L;
    private User user;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructor
     * 
     * @param user
     *            {@link User}
     */
    public UserDetailsImpl(User user) {
        this.user = user;
        authorities = getRoles();
    }

    /**
     * Adds the role of user to all {@link User}
     * 
     * @return {@link Collection}<{@link GrantedAuthority}>
     */
    private Collection<GrantedAuthority> getRoles() {
        GrantedAuthority authority = new GrantedAuthority() {

            private static final long serialVersionUID = 2788841175653165322L;

            @Override
            public String getAuthority() {
                return "ROLE_USER";
            }
        };

        return new HashSet<>(Arrays.asList(authority));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
