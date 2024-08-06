package se.scrum.domain.user;

import org.apache.log4j.Logger;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import se.scrum.controller.user.UserController;

@Component
public class UserResourceAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    private Logger logger = Logger.getLogger(UserResource.class);

    @Override
    public EntityModel<User> toModel(User entity) {
        EntityModel<User> model = EntityModel.of(entity);
        try {
            model.add(
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getUser(entity.getId())).withSelfRel()
            );
        } catch (Exception e) {
            logger.error("Error creating link", e);
        }
        logger.debug("created resource: " + model);
        return model;
    }
}
