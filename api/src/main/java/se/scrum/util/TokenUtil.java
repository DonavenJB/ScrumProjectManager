package se.scrum.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.codec.Hex;

import se.scrum.domain.user.User;

/**
 * 
 * Util class for making tokens and validation of tokens
 * 
 * @author fidde
 *
 **/
public class TokenUtil {

    private static final String SECRET = "secret";
    private static MessageDigest digest;
    private static Logger logger = Logger.getLogger(TokenUtil.class);

    /**
     * Takes a {@link User} and returns a token based on the time and name of
     * the user.
     * 
     * @param user
     *            User
     * @return new token that is valid for 1h
     * @throws NullPointerException
     *             If {@link User} has a name of null
     */
    public static String getToken(User user) throws NullPointerException {
        StringBuilder token = new StringBuilder(user.getName());
        token.append(":");

        // by default a token is good for 1h
        long expires = System.currentTimeMillis() + 1000L * 60 * 60;
        token.append(expires);
        token.append(":");
        token.append(getKey(user, expires));
        String string = token.toString();

        logger.debug("token made: " + string);
        return string;
    }

    /**
     * Creates a key for the token
     * 
     * @param user
     * @param expires
     * @return a new key
     * @throws NullPointerException
     *             if {@link User} has a name of null
     */
    public static String getKey(User user, long expires)
            throws NullPointerException {
        StringBuilder key = new StringBuilder(user.getName());
        key.append(":");
        key.append(user.getPassword());
        key.append(":");
        key.append(SECRET);

        try {
            digest = MessageDigest.getInstance("SHA-1");

        } catch (NoSuchAlgorithmException e) {
            logger.debug(e.getMessage());
        }
        byte[] keyBytes = digest.digest(key.toString().getBytes());
        char[] encode = Hex.encode(keyBytes);

        return new String(encode);
    }

    /**
     * Checks if token is valid
     * 
     * @param token
     *            string formated for the api
     * @param user
     *            user that is connected to the token
     * @return true if token is valid for the given user
     * @throws NullPointerException
     *             if the users name is null
     */
    public static boolean isValidToken(String token, User user)
            throws NullPointerException {
        String[] split = token.split(":");
        long expires = Long.parseLong(split[1]);
        String key = split[2];
        long time = System.currentTimeMillis();

        // if the token has expired return false
        if (expires < time) {
            logger.debug(String.format("token expired, time: %s token: %s",
                    time, token));
            return false;
        }

        String key2 = getKey(user, expires);
        boolean equals = key.equals(key2);
        logger.debug(String.format("%s == %s = %s", key, key2, equals));

        return equals;
    }

    /**
     * Gets the username from the provided token string
     * 
     * @param token
     * @return name of the {@link User} that is connected to the token
     */
    public static String getUsernameFromToken(String token) {
        String[] split = token.split(":");
        return split[0];
    }
}