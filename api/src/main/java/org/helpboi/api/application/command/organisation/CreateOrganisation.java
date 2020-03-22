package org.helpboi.api.application.command.organisation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.organisation.Organisation;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class CreateOrganisation  extends Command<Organisation> {

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
            Long userId) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CreateOrganisation))
			return false;
		CreateOrganisation other = (CreateOrganisation) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CreateOrganisation [name=" + name + ", zipcode=" + zipcode + ", city=" + city + ", address=" + address
				+ "]";
	}


}
