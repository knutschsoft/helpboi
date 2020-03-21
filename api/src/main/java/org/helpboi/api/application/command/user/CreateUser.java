package org.helpboi.api.application.command.user;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.user.User;

public class CreateUser extends Command<String> {

    @NotNull
    @NotBlank
    @Size(min = 0, max = 255)
    private String name;

    public CreateUser(User user) {
        this.name = user.getName();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateUser createUser = (CreateUser) o;
        return Objects.equals(name, createUser.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "CreateUser{" +
                "name='" + name + '\'' +
                '}';
    }
}
