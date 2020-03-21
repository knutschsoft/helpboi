package org.helpboi.api.application.handler.user;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.user.DeleteUser;
import org.helpboi.api.application.persistence.UserRepository;
import org.helpboi.api.domain.model.user.User;

@Singleton
public class DeleteUserHandler implements CommandHandler<DeleteUser> {

    @Inject
    private UserRepository userRepository;

    @Override
    @Transactional
    public void handle(DeleteUser command) {
        Optional<User> user = userRepository.findById(command.getId());
        if (user.isPresent()) {
        	command.notFound();	
        } else {
        	command.resolve(null);
        }
    }
}
