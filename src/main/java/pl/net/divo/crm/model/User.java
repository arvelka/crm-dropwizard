package pl.net.divo.crm.model;

import java.security.Principal;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User implements Principal {

	@NotEmpty
	private int id;

	@NotEmpty
	@JsonProperty
	private String username;

	@NotEmpty
	@JsonProperty
	private String password;

	@NotEmpty
	@JsonProperty
	private String name;

	@NotEmpty
	@JsonProperty
	private String displayRole;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getName() {
		return name;
	}

	public User setName(String displayName) {
		this.name = displayName;
		return this;
	}

	public String getDisplayRole() {
		return displayRole;
	}

	public User setDisplayRole(String displayRole) {
		this.displayRole = displayRole;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;

		User that = (User) o;

		if (!getUsername().equals(that.getUsername())) return false;
		if (!getPassword().equals(that.getPassword())) return false;
		if (!getName().equals(that.getName())) return false;
		if (!getDisplayRole().equals(that.getDisplayRole())) return false;

		return true;
	}
}