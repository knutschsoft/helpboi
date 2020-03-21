package org.helpboi.api.application.command.user;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.user.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CreateUser extends Command<User> {

	@Email
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String email;
    @NotNull
    @NotBlank
    @Size(max = 255)
    @JsonIgnore
    private String password;

    @NotBlank
    @Size(max = 255)
    private String firstname;
    @NotBlank
    @Size(max = 255)
    private String lastname;

    @NotBlank
    @Size(max = 255)
    private String phone;

	public CreateUser(
			String email,
	        String password,
	        String firstname,
	        String lastname,
	        String phone
	) {
		Objects.requireNonNull(email);
        Objects.requireNonNull(password);
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}

	public String getFirstName() {
		return lastname;
	}

	public String getLastName() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CreateUser))
			return false;
		CreateUser other = (CreateUser) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CreateUser [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password="
				+ password + ", phone=" + phone + "]";
	}
}
