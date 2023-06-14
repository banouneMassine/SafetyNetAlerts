package com.safetyNet.config;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class LoadDataFromFileTest implements LoadDataTest {

	@Autowired
	private PersonsRepository personsRepository;
	
	@Autowired
	MedicalRecordRepository medicalRecordRepository;
	
	@Autowired
	FirestationsRepository firestationsRepository;
	
	private static final Logger logger = LogManager.getLogger("LoadDataFromFileTest");
	
	@Value("${json.test.file.path}")
    private String jsonFilePath;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	@PostConstruct
	public void getData() {
		logger.info("Importer les données du fichier json");
		File jsonFile = new File(jsonFilePath);
		JsonNode jsonNode = null;
		try {
			// Lecture du fichier JSON
			jsonNode = objectMapper.readTree(jsonFile);
			loadPersons(jsonNode);
			loadMedicalRecords(jsonNode);
			loadFireStation(jsonNode);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("erreur de récupérartion des données json");
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



