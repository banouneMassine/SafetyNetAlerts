package com.safetyNet.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FirestationsModel {
	
	@JsonProperty("address")
	public String address;
	@JsonProperty("station")
	public int station ;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

	
	

}
