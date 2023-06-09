package com.safetyNet.model;

import com.fasterxml.jackson.annotation.JsonProperty;


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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
