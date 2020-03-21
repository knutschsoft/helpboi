package org.helpboi.api.application.command.patient;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.patient.Patient;
import org.helpboi.api.domain.model.patient.PatientGender;
import org.helpboi.api.domain.model.patient.PatientStatus;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class CreatePatient extends Command<Patient> {

    @Min(1L)
    @NotNull
    private Long organisationId;

    @NotBlank
    @Size(max = 255)
    private String firstname;
    @NotBlank
    @Size(max = 255)
    private String lastname;

    private PatientGender gender;
    @NotBlank
    @Size(max = 255)
    private String        phone;

    private ZonedDateTime dateOfBirth;

    @NotBlank
    @Size(max = 255)
    private String zipcode;
    @NotBlank
    @Size(max = 255)
    private String city;
    @NotBlank
    @Size(max = 255)
    private String address;

    private PatientStatus status;

    private String notes;

    public CreatePatient(
            Long organisationId,
            String firstname,
            String lastname,
            PatientGender gender,
            String phone,
            ZonedDateTime dateOfBirth,
            String zipcode,
            String city,
            String address,
            PatientStatus status,
            String notes
    ) {
        this.organisationId = organisationId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.zipcode = zipcode;
        this.city = city;
        this.address = address;
        this.status = status;
        this.notes = notes;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public PatientGender getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public ZonedDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public PatientStatus getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreatePatient that = (CreatePatient) o;
        return Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                gender == that.gender &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(zipcode, that.zipcode) &&
                Objects.equals(city, that.city) &&
                Objects.equals(address, that.address) &&
                status == that.status &&
                Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, gender, phone, dateOfBirth, zipcode, city, address, status, notes);
    }

    @Override
    public String toString() {
        return "CreatePatient{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", notes='" + notes + '\'' +
                '}';
    }
}
