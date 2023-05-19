package com.safetyNet.model;


import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MedicalRecordsModel {
	@JsonProperty("firstName")
	public String firstName ;
	
	@JsonProperty("lastName")
	public String lastName ;
	
	@JsonProperty("birthdate")
	public String birthdate ;
	
	@JsonProperty("medications")
	List<String> medications ;
	
	@JsonProperty("allergies")
	List<String> allergies ;
}
