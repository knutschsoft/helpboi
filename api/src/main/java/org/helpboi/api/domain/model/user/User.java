package org.helpboi.api.domain.model.user;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Min(1L)
    @Column(name = "organisation_id")
    private Long organisationId;

    @Column(name = "is_admin")
    private Boolean isAdmin;
    @Column(name = "is_verified")
    private Boolean isVerified;

    public User() {
    }

    public User(
        Long id,
        String email,
        String password,
        String firstname,
        String lastname,
        String phone
    ) {
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);

        this.id = id;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    public Boolean isVerified() {
        return isVerified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", organisationId=" + organisationId +
                ", isAdmin=" + isAdmin +
                ", isVerified=" + isVerified +
                '}';
    }
}
