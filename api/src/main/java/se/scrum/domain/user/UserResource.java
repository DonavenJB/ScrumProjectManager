package se.scrum.domain.user;

import org.springframework.hateoas.RepresentationModel;

/**
 * Hypermedia representation of {@link User}
 * 
 */
public class UserResource extends RepresentationModel<UserResource> {
    private final User user;

    public UserResource(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
