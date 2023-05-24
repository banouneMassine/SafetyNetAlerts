package com.safetyNet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface LoadData {
	
	void getData();
	void loadPersons(JsonNode jsonNode) throws JsonProcessingException, IllegalArgumentException;
	void loadMedicalRecords(JsonNode jsonNode) throws JsonProcessingException, IllegalArgumentException;
	void loadFireStation(JsonNode jsonNode) throws JsonProcessingException, IllegalArgumentException;
	
}
