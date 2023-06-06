package com.safetyNet.DTO;

import java.util.ArrayList;
import java.util.List;

import com.safetyNet.model.PersonsModel;

public class PersonInfoDTO {

	public String lastName;

	public String address ;
	
	public int   age ;
	
	public String email;
	
	public List<String> medications ;
	
	public List<PersonsModel> familles = new ArrayList<>(); 
	
	public List<PersonsModel> getFamilles() {
		return familles;
	}

	public void setFamilles(List<PersonsModel> familles) {
		this.familles = familles;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<String> allergies ;
}
