package com.safetyNet.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.safetyNet.model.MedicalRecordsModel;
import com.safetyNet.model.PersonsModel;
import com.safetyNet.repository.MedicalRecordRepository;
import com.safetyNet.repository.PersonsRepository;

import jakarta.annotation.PostConstruct;

@Service
public class LoadDataFromFile implements LoadData {

	@Autowired
	private PersonsRepository personsRepository;
	
	@Autowired
	private MedicalRecordRepository medicalRecordRepository ;
	
	@Value("${json.filePath}")
	private String filePath;

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
			// Récupération du tableau "persons" dans le JSON
			JsonNode personsNode = jsonNode.get("persons");
			// Conversion des données JSON en liste d'objets Person
			
			
			for (JsonNode personNode : personsNode) {
				PersonsModel person = objectMapper.treeToValue(personNode, PersonsModel.class);
				personsRepository.addPerson(person);
			}
			JsonNode medicalRecordsNode = jsonNode.get("medicalrecords");
			// Conversion des données JSON en liste d'objets Person
			
			for(JsonNode iterationMedicalRecordNode  : medicalRecordsNode)
			{
				MedicalRecordsModel MedicalRecord = objectMapper.treeToValue(iterationMedicalRecordNode, MedicalRecordsModel.class);
				medicalRecordRepository.addMedicalRecord(MedicalRecord);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// récupérer les données du fichiers json et les stocker dans les repositories
		// logique de récupération depuis le fichier json
		// PersonsModel person = new PersonsModel();
		// construction de la person
		// personsRepository.addPerson(person)
	}
	@Override
	public void addPersonInFile(PersonsModel person)
	{
		/*ObjectNode newPersonNode = objectMapper.createObjectNode();
		newPersonNode.put("firstName",person.firstName);
		newPersonNode.put("lastName" ,person.lastName );
		newPersonNode.put("address" ,person.address );
		newPersonNode.put("city" ,person.city );
		newPersonNode.put("zip" ,person.zip );
		newPersonNode.put("phone" ,person.phone );
		newPersonNode.put("email" ,person.email );*/
		
		
	}


}
