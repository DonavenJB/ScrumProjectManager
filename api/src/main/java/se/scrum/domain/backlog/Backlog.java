package se.scrum.domain.backlog;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.data.annotation.Transient;

import se.scrum.domain.container.ProjectContainer;
import se.scrum.domain.sprint.Sprint;
import se.scrum.domain.userstory.UserStory;

/**
 * Container for storing unfinished {@link UserStory}
 * 
 * @author fidde
 *
 */
public class Backlog extends ProjectContainer {

    @Transient
    private Logger log = Logger.getLogger(Backlog.class);
    private String owner;
    private Set<String> teamMembers;
    private Set<String> stakeholders;
    private Set<Sprint> sprints;

    /**
     * Constructor
     */
    public Backlog() {
        teamMembers = new HashSet<>();
        stakeholders = new HashSet<>();
        sprints = new HashSet<>();
        log.debug("backlog created");
    }

    public Set<String> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(Set<String> users) {
        this.teamMembers = users;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Set<String> getStakeholders() {
        return stakeholders;
    }

    public void setStakeholders(Set<String> stakeholders) {
        this.stakeholders = stakeholders;
    }

    public Set<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(Set<Sprint> sprints) {
        this.sprints = sprints;
    }

    @Override
    public String toString() {
        return "Backlog [owner=" + owner + ", teamMembers=" + teamMembers
                + ", stakeholders=" + stakeholders + ", sprints=" + sprints
                + ", id=" + id + ", name=" + name + ", userStories="
                + userStories + "]";
    }

}