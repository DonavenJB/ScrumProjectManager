package se.scrum.domain.sprint;

import org.apache.log4j.Logger;
import org.springframework.data.annotation.Transient;

import se.scrum.domain.container.ProjectContainer;

/**
 * Sprint entity
 * 
 * @author Johan, fidde
 */
public class Sprint extends ProjectContainer {

    @Transient
    private Logger log = Logger.getLogger(Sprint.class);

    private long startDate;
    private long endDate;
    private String details;
    private SprintState state;

    /**
     * Constructor
     */
    public Sprint() {
        setState(SprintState.IN_PLANNING);
        log.debug("sprint created");
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
        log.debug("sprint startDate set to: " + startDate);
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
        log.debug("sprint endDate set to: " + endDate);
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
        log.debug("sprint details set to: " + details);
    }

    public SprintState getState() {
        return state;
    }

    public void setState(SprintState state) {
        this.state = state;
        log.debug("sprint state set to: " + state);
    }

    @Override
    public String toString() {
        return "Sprint [name=" + name + ", startDate=" + startDate
                + ", endDate=" + endDate + ", details=" + details + ", state="
                + state + "]";
    }

}
