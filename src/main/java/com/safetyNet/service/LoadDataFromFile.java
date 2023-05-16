package com.safetyNet.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNet.model.PersonsModel;
import com.safetyNet.repository.PersonsRepository;

import jakarta.annotation.PostConstruct;

@Service
public class LoadDataFromFile implements LoadData {

	@Autowired
	private PersonsRepository personsRepository;
	
	private final File jsonFile = new File("C:\\Users\\banou\\OneDrive\\Bureau\\formation java\\projet 5\\SafetyNetAlerts\\src\\main\\resources\\data.json");

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
	             
	             
	             for (JsonNode personNode : personsNode) 
	             {
	            	 PersonsModel person = objectMapper.treeToValue(personNode, PersonsModel.class);
	                 personsRepository.addPerson(person);
	             }
	            
			} catch (IOException e) {
	            e.printStackTrace();
	        }

		
		// récupérer les données du fichiers json et les stocker dans les repositories
		//logique de récupération depuis le fichier json
		//PersonsModel person = new PersonsModel();
		//construction de la person
		//personsRepository.addPerson(person)
	}

}
