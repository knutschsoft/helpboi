package org.helpboi.api.application.handler.user;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.user.DeleteUser;
import org.helpboi.api.application.persistence.UserRepository;
import org.helpboi.api.domain.exception.NotFoundException;

@Singleton
public class DeleteUserHandler implements CommandHandler<DeleteUser> {

    @Inject
    private UserRepository userRepository;

    @Override
    @Transactional
    public void handle(DeleteUser command) {
        Long userId = command.getId();
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "User id: %s not found", userId)));
       command.resolve(null);
    }
}
