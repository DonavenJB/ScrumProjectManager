package se.scrum.util;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import se.scrum.domain.user.User;

/**
 * Validation util
 * 
 * @author fidde
 *
 */
public class ValidationUtil {

    public static final int USERNAME_MIN_LENGTH = 1;
    public static final int PASSWORD_MIN_LENGTH = 1;
    public static final int USERNAME_MAX_LENGTH = 25;
    public static final int PASSWORD_MAX_LENGTH = 25;

    /**
     * Checks if object is null
     * 
     * @param obj
     *            object to be tested
     * @param msg
     *            message to be added to exception if object is found to be null
     * @throws NullPointerException
     *             if the object is null
     */
    public static void checkForNull(Object obj, String msg)
            throws NullPointerException {
        if (obj == null) {
            throw new NullPointerException(msg);
        }
    }

    /**
     * Checks if a {@link User} is valid
     * 
     * @param user
     *            user to be checked
     * @throws Exception
     *             if the user is invalid
     */
    public static void validateUser(User user) throws Exception {
        String name = user.getName();
        String password = user.getPassword();

        if (name == null || name.length() < USERNAME_MIN_LENGTH
                || name.length() > USERNAME_MAX_LENGTH) {
            throw new Exception("Username invalid");

        } else if (password == null || password.length() < PASSWORD_MIN_LENGTH
                || password.length() > PASSWORD_MAX_LENGTH) {
            throw new Exception("Password invalid");

        }
    }

    /**
     * Checks if user has access to the requested resource
     * 
     * @param user
     *            user to be tested
     * @throws AccessDeniedException
     *             if user does not have access
     */
    public static void checkIfUserHasAccess(User user)
            throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if (authentication == null) {
            throw new AccessDeniedException("Not authenticated");
        }
        boolean hasRightToAccess = user.getName().equals(
                authentication.getName());

        if (hasRightToAccess) {
            return;
        }
        throw new AccessDeniedException("Not allowed to access this user");
    }
}
