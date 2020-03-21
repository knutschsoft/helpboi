package org.helpboi.api.application.command.patient;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.patient.Patient;
import org.helpboi.api.domain.model.patient.PatientGender;
import org.helpboi.api.domain.model.patient.PatientStatus;

public class CreatePatient extends Command<Patient> {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String firstname;
    @NotBlank
    @Size(max = 255)
    private String lastname;

    @Enumerated(EnumType.STRING)
    private PatientGender gender;
    @NotBlank
    @Size(max = 255)
    private String phone;

    @Column(name = "date_of_birth")
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

    @Enumerated(EnumType.STRING)
    private PatientStatus status;

    @Lob
    private String notes;
    
    public CreatePatient(
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

	public Long getId() {
		return id;
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
	public String toString() {
		return "CreatePatient [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", gender=" + gender
				+ ", phone=" + phone + ", dateOfBirth=" + dateOfBirth + ", zipcode=" + zipcode + ", city=" + city
				+ ", address=" + address + ", status=" + status + ", notes=" + notes + "]";
	}
    
    
}
