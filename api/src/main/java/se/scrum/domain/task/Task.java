package se.scrum.domain.task;

import org.apache.log4j.Logger;
import org.springframework.data.annotation.Transient;

/**
 * Task entity
 * 
 * @author fidde
 *
 */
public class Task {

    @Transient
    private Logger log = Logger.getLogger(Task.class);

    private String name;
    private String description;
    private TaskStatus state;
    private int estimate;
    private String assignedTo;

    /**
     * Constructor
     */
    public Task() {
        setState(TaskStatus.TODO);
        log.debug("task created");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getState() {
        return state;
    }

    public int getEstimate() {
        return estimate;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setName(String name) {
        this.name = name;
        log.debug("task name set to: " + name);
    }

    public void setDescription(String description) {
        this.description = description;
        log.debug("task description set to: " + description);
    }

    public void setState(TaskStatus state) {
        this.state = state;
        log.debug("task status set to: " + state);
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
        log.debug("task estimate set to: " + estimate);
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
        log.debug("task assignedTo set to: " + assignedTo);
    }

    @Override
    public String toString() {
        return "Task [name=" + name + ", description=" + description
                + ", status=" + state + ", estimate=" + estimate
                + ", assignedTo=" + assignedTo + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((assignedTo == null) ? 0 : assignedTo.hashCode());
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + estimate;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
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
        Task other = (Task) obj;
        if (assignedTo == null) {
            if (other.assignedTo != null)
                return false;
        } else if (!assignedTo.equals(other.assignedTo))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (estimate != other.estimate)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (state != other.state)
            return false;
        return true;
    }

}
