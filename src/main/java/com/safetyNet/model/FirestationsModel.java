package com.safetyNet.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FirestationsModel {
	
	@JsonProperty("address")
	public String address;
	
	@JsonProperty("station")
	public int station ;
	

}
