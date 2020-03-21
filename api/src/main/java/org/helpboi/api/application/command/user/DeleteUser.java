package org.helpboi.api.application.command.user;

import javax.validation.constraints.NotNull;

import org.helpboi.api.application.Command;

public class DeleteUser extends Command<String> {

    @NotNull
    private Long id;

    public DeleteUser(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "DeleteUser{" +
                "id='" + id + '\'' +
                '}';
    }
}
