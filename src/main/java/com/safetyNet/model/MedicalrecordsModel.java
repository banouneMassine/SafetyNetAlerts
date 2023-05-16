package com.safetyNet.model;


import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MedicalrecordsModel {
	@JsonProperty("firstName")
	public String firstName ;
	
	@JsonProperty("lastName")
	public String lastName ;
	
	@JsonProperty("birthdate")
	public String birthdate ;
	
	@JsonProperty("medications")
	Map<String,String> medications ;
	
	@JsonProperty("allergies")
	Map<String,String> allergies ;
	
	

}
