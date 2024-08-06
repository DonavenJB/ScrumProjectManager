package se.scrum.domain.user;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import se.scrum.domain.backlog.Backlog;

/**
 * User entity
 * 
 * @author fidde
 *
 */
@Document(collection = "users")
public class User {

    @Transient
    private Logger log = Logger.getLogger(User.class);

    @Id
    private String id;
    @Indexed
    private String name;
    private String password;
    private Set<Backlog> backlogs;

    /**
     * Default constructor
     */
    public User() {
        backlogs = new HashSet<>();

        log.debug("user created");
    }

    /**
     * Constructor
     * 
     * @param name
     * @param password
     */
    public User(String name, String password) {
        super();
        this.name = name;
        this.password = password;
        backlogs = new HashSet<>();

        log.debug("user created");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        log.debug("user name set to: " + name);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        log.debug("user password set to: " + password);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        log.debug("user db id set to: " + id);
    }

    public Set<Backlog> getBacklogs() {
        return backlogs;
    }

    public void setBacklogs(Set<Backlog> backlogs) {
        this.backlogs = backlogs;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password
                + ", backlogs=" + backlogs + "]";
    }

}
