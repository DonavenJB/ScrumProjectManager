package se.scrum.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import se.scrum.domain.user.User;

/**
 * User repository
 * 
 * @author fidde
 *
 */
public interface UserRepo extends MongoRepository<User, String> {

    /**
     * Find user by name
     * 
     * @param name
     *            user name
     * @return {@link User}
     */
    User findByName(String name);

    /**
     * Find user by name and password
     * 
     * @param name
     *            user name
     * @param password
     *            password
     * @return {@link User}
     */
    User findByNameAndPassword(String name, String password);
}
