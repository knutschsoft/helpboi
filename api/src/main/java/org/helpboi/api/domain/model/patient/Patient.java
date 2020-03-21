package org.helpboi.api.domain.model.patient;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.helpboi.api.domain.shared.Gender;
import org.helpboi.api.domain.shared.Status;

@Entity(name = "patient")
public class Patient {

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
    private Gender gender;
    @NotBlank
    @Size(max = 255)
    private String phone;

    @Column(name="date_of_birth")
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
    private Status status;

    @Lob
    private String notes;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "patient_symptom", joinColumns = @JoinColumn(name = "patient_id"))
    private Set<Long> symptomIds = new LinkedHashSet<>();

    public Patient() {
    }

    public Patient(
            Long id,
            String firstname,
            String lastname,
            Gender gender,
            String phone,
            ZonedDateTime dateOfBirth,
            String zipcode,
            String city,
            String address,
            String notes
    ) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.zipcode = zipcode;
        this.city = city;
        this.address = address;
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

    public Gender getGender() {
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

    public Status getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }

    public Set<Long> getSymptomIds() {
        return Collections.unmodifiableSet(symptomIds);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
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
