package se.scrum.domain.container;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import se.scrum.domain.backlog.Backlog;
import se.scrum.domain.sprint.Sprint;
import se.scrum.domain.userstory.UserStory;

/**
 * Container superclass for {@link Backlog} and {@link Sprint}
 */
@Document
public abstract class ProjectContainer {

    @Transient
    private Logger log = Logger.getLogger(ProjectContainer.class);
    protected String id;
    protected String name;
    protected Set<UserStory> userStories;

    /**
     * Constructor
     */
    public ProjectContainer() {
        id = UUID.randomUUID().toString();
        userStories = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        log.debug("id set to: " + id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        log.debug("name set to: " + name);
    }

    public Set<UserStory> getUserStories() {
        return userStories;
    }

    public void setUserStories(Set<UserStory> userStories) {
        this.userStories = userStories;
        log.debug("userstories set to: " + userStories);
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
        ProjectContainer other = (ProjectContainer) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ProjectContainer [id=" + id + ", name=" + name + ", userStories=" + userStories + "]";
    }
}
