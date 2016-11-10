package pl.net.divo.crm.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Role {
	
	@NotEmpty
	@JsonProperty
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Role)) return false;

		Role that = (Role) o;
		if (!getName().equals(that.getName())) return false;
		return true;
	}
}
