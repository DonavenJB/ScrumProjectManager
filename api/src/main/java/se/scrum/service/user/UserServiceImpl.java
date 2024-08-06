package se.scrum.service.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.scrum.domain.backlog.Backlog;
import se.scrum.domain.user.User;
import se.scrum.repo.UserRepo;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link UserService}
 * 
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger log = Logger.getLogger(UserServiceImpl.class);
    private UserRepo repo;

    /**
     * Default constructor
     */
    public UserServiceImpl() {
        log.debug("UserServiceImpl created");
    }

    /**
     * Constructor
     * 
     * @param repo {@link UserRepo}
     */
    @Autowired
    public UserServiceImpl(UserRepo repo) {
        super();
        this.repo = repo;
        log.debug("UserServiceImpl created");
    }

    @Override
    public User getUser(String id) {
        log.debug("get: " + id);
        Optional<User> user = repo.findById(id);
        if (user.isPresent()) {
            log.debug("found: " + user.get());
            return user.get();
        } else {
            log.debug("User not found");
            return null;
        }
    }

    @Override
    public void removeUser(String id) {
        log.debug("remove: " + id);
        repo.deleteById(id);
    }

    /**
     * Adding a user with no backlog will add a default backlog
     */
    @Override
    public User addUser(User user) {
        // all users must have at least 1 backlog
        if (user != null && user.getBacklogs().size() < 1) {
            Backlog backlog = new Backlog();
            String name = "My backlog";
            backlog.setName(name);
            log.debug("setting backlog name to: " + name);
            user.getBacklogs().add(backlog);
        }
        log.debug("add: " + user);
        return repo.save(user);
    }

    @Override
    public void dropAllUsers() {
        repo.deleteAll();
        log.debug("removed all users");
    }

    @Override
    public User getUserByName(String username) {
        User result = repo.findByName(username);
        log.debug("get: " + result);
        return result;
    }

    @Override
    public User getUserByNameAndPassword(String username, String password) {
        User result = repo.findByNameAndPassword(username, password);
        log.debug("get: " + result);
        return result;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = repo.findAll();
        log.debug("getAll: " + result);
        return result;
    }
}
