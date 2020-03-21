package org.helpboi.api.application.handler.user;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.user.DeleteUser;
import org.helpboi.api.application.persistence.UserRepository;

@Singleton
public class DeleteUserHandler implements CommandHandler<DeleteUser> {

    @Inject
    private UserRepository userRepository;

    @Override
    @Transactional
    public void handle(DeleteUser command) {
        userRepository.deleteById(command.getId());
        command.resolve(null);
    }
}
