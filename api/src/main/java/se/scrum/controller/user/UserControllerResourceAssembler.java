package se.scrum.controller.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UserControllerResourceAssembler implements RepresentationModelAssembler<UserController, EntityModel<UserController>> {

    @Override
    public EntityModel<UserController> toModel(UserController entity) {
        return EntityModel.of(entity);
    }
}
