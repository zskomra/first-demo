package projects.portfoliodemo.converter;

import projects.portfoliodemo.domain.model.User;
import projects.portfoliodemo.web.command.RegisterUserCommand;

public class UserConverter {

    public User from(RegisterUserCommand registerUserCommand) {
        return User.builder()
                .username(registerUserCommand.getUsername())
                .password(registerUserCommand.getPassword())
                .build();
    }
}
