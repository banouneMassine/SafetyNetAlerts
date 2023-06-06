package com.safetyNet.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;



public class MedicalRecordsDTO {
	@JsonProperty("firstName")
	public String firstName ;
	
	@JsonProperty("lastName")
	public String lastName ;
	
	@JsonProperty("birthdate")
	public String birthdate ;
	
	@JsonProperty("medications")
	public List<String> medications ;
	
	@JsonProperty("allergies")
	public List<String> allergies ;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	
}
