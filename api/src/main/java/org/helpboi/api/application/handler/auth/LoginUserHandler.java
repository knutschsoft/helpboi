package org.helpboi.api.application.handler.auth;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.auth.LoginUser;
import org.helpboi.api.application.persistence.UserRepository;
import org.helpboi.api.domain.exception.BusinessException;
import org.helpboi.api.domain.model.user.User;

import io.micronaut.security.authentication.providers.PasswordEncoder;

@Singleton
public class LoginUserHandler implements CommandHandler<LoginUser> {

    @Inject
    private UserRepository  userRepository;
    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void handle(LoginUser command) throws Exception {
        String email = command.getEmail();
        String password = command.getPassword();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(
                        "Email or password is wrong"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BusinessException(
                    "Email or password is wrong");
        }
        command.resolve(user);
    }
}
