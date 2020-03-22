package org.helpboi.api.application.command.organisation;

import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.organisation.Organisation;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class CreateOrganisation extends Command<Organisation> {

    @NotNull
    @NotBlank
    @Size(max = 255)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 255)
    private String zipcode;
    
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String city;
    
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String address;

    @Min(1)
    @NotNull
    private Long userId;

    public CreateOrganisation(
    		String name,
            String zipcode,
            String city,
            String address,
            Long userId
    ) {
        this.name = name;
        this.zipcode = zipcode;
        this.city = city;
        this.address = address;
        this.userId = userId;
    }

	public String getName() {
		return name;
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

	public Long getUserId() {
		return userId;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateOrganisation that = (CreateOrganisation) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(zipcode, that.zipcode) &&
                Objects.equals(city, that.city) &&
                Objects.equals(address, that.address) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, zipcode, city, address, userId);
    }

    @Override
    public String toString() {
        return "CreateOrganisation{" +
                "name='" + name + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", userId=" + userId +
                '}';
    }
}
