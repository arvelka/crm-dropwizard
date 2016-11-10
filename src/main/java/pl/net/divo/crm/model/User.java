package pl.net.divo.crm.model;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class User {
	
	@JsonProperty
	private String username;
	
	@JsonProperty
	private String password;
	
	@JsonProperty
	private String displayName;
}