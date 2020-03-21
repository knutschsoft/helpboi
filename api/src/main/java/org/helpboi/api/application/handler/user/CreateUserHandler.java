package org.helpboi.api.application.handler.user;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.user.CreateUser;
import org.helpboi.api.application.persistence.UserRepository;
import org.helpboi.api.domain.exception.BusinessException;
import org.helpboi.api.domain.model.user.User;

import io.micronaut.security.authentication.providers.PasswordEncoder;

@Singleton
public class CreateUserHandler implements CommandHandler<CreateUser> {

    @Inject
    private UserRepository  userRepository;
    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void handle(CreateUser command) {
        String email = command.getEmail();
        String password = command.getPassword();
        String firstname = command.getFirstname();
        String lastname = command.getLastname();
        String phone = command.getPhone();

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new BusinessException(
                    String.format("A user with email: '%s' already exists.", email));
        }

        password = passwordEncoder.encode(password);
        User finalUser = new User(
                null, email, password, firstname, lastname, phone);
        finalUser = userRepository.save(finalUser);

        command.resolve(finalUser);
    }
}
