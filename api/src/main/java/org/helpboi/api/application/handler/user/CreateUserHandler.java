package org.helpboi.api.application.handler.user;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.user.CreateUser;
import org.helpboi.api.application.persistence.UserRepository;
import org.helpboi.api.domain.model.user.User;

@Singleton
public class CreateUserHandler implements CommandHandler<CreateUser> {

    @Inject
    private UserRepository userRepository;

    @Override
    @Transactional
    public void handle(CreateUser command) {
        User user = new User(null, command.getName());
        user = userRepository.save(user);
        command.resolve("User " + command.getName() + ", you#ve got id: " + user.getId());
    }
}
