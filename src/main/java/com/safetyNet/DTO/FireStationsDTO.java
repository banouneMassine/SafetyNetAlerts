package com.safetyNet.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FireStationsDTO {
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
