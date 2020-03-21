package org.helpboi.api.application.command.auth;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.user.User;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class LoginUser extends Command<User> {

    @NotNull
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String password;

    public LoginUser(
            String email,
            String password
    ) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoginUser login = (LoginUser) o;
        return Objects.equals(email, login.email) &&
                Objects.equals(password, login.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "Login{" +
                "email='" + email + '\'' +
                '}';
    }
}
