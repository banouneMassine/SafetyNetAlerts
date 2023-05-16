package com.safetyNet.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PersonsModel {
	
	@JsonProperty("firstName")
	public String firstName ;
	
	@JsonProperty("lastName")
	public String lastName;
	
	@JsonProperty("address")
	public String address ;
	
	@JsonProperty("city")
	public String city;
	
	@JsonProperty("zip")
	public int zip ;
	
	@JsonProperty("phone")
	public String phone;
	
	@JsonProperty("email")
	public String email;
	
	
}
