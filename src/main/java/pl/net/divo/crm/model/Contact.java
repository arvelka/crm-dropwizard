package pl.net.divo.crm.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contact {
	
	@NotEmpty
	@JsonProperty
	private String name;
	
	@NotEmpty
	@JsonProperty
	private String surname;
	
	@NotEmpty
	@JsonProperty
	private String companyName;
	
	@NotEmpty
	@JsonProperty
	private String email;
	
	@NotEmpty
	@JsonProperty
	private String phone;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurName(String surname) {
		this.surname = surname;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Contact)) return false;

		Contact that = (Contact) o;
		if (!getName().equals(that.getName())) return false;
		if (!getSurname().equals(that.getSurname())) return false;
		if (!getCompanyName().equals(that.getCompanyName())) return false;
		if (!getPhone().equals(that.getPhone())) return false;
		if (!getEmail().equals(that.getEmail())) return false;
		return true;
	}
}
