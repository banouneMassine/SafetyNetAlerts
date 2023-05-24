package com.safetyNet.service;

import java.io.File;
import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.safetyNet.model.FirestationsModel;
import com.safetyNet.model.MedicalRecordsModel;
import com.safetyNet.model.PersonsModel;
import com.safetyNet.repository.FirestationsRepository;
import com.safetyNet.repository.MedicalRecordRepository;
import com.safetyNet.repository.PersonsRepository;

import jakarta.annotation.PostConstruct;

@Service
public class LoadDataFromFile implements LoadData {

	@Autowired
	private PersonsRepository personsRepository;
	
	@Autowired
	private MedicalRecordRepository medicalRecordRepository ;
	
	@Autowired
	private FirestationsRepository firestationsRepository ;
	
	//@Value("${json.filePath}")
	//private String filePath;

	private final File jsonFile = new File(
			"C:\\Users\\banou\\OneDrive\\Bureau\\formation java\\projet 5\\SafetyNetAlerts\\src\\main\\resources\\data.json");

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	@PostConstruct
	public void getData() {

		JsonNode jsonNode = null;
		try {
			// Lecture du fichier JSON
			jsonNode = objectMapper.readTree(jsonFile);
			loadPersons(jsonNode);
			loadMedicalRecords(jsonNode);
			loadFireStation(jsonNode);
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void loadPersons(JsonNode jsonNode) throws JsonProcessingException, IllegalArgumentException {
		JsonNode personsNode = jsonNode.get("persons");
		// Conversion des données JSON en liste d'objets Person
		
		for (JsonNode personNode : personsNode) {
			PersonsModel person = objectMapper.treeToValue(personNode, PersonsModel.class);
			personsRepository.addPerson(person);
		}
		
	}

	@Override
	public void loadMedicalRecords(JsonNode jsonNode) throws JsonProcessingException, IllegalArgumentException {
		JsonNode medicalRecordsNode = jsonNode.get("medicalrecords");
		// Conversion des données JSON en liste d'objets medicalrecords
		
		for(JsonNode iterationMedicalRecordNode  : medicalRecordsNode)
		{
			MedicalRecordsModel medicalRecord = objectMapper.treeToValue(iterationMedicalRecordNode, MedicalRecordsModel.class);
			medicalRecordRepository.addMedicalRecord(medicalRecord);
		}
		
	}

	@Override
	public void loadFireStation(JsonNode jsonNode) throws JsonProcessingException, IllegalArgumentException {
		JsonNode firestationsNode = jsonNode.get("firestations");
		// Conversion des données JSON en liste d'objets firestations
		
		for(JsonNode iterationFireStationsNodeNode  : firestationsNode)
		{
			FirestationsModel firestation = objectMapper.treeToValue(iterationFireStationsNodeNode, FirestationsModel.class);
			firestationsRepository.addFirestation(firestation);
		}
		
	}

	
	


}
