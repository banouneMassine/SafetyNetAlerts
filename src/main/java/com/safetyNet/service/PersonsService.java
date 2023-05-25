package com.safetyNet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.safetyNet.model.PersonsModel;
import com.safetyNet.repository.PersonsRepository;

import lombok.Data;

@Data
@Service
public class PersonsService {
	@Autowired
	PersonsRepository personsRepository;

	public List<PersonsModel> getPersons() {
		return personsRepository.findAll();
	}

	public PersonsModel getPerson(String firstName, String lastName) {
		return personsRepository.findByfirstName(firstName, lastName);
	}

	// ajouter une personne 
	public ResponseEntity<String> addPerson(PersonsModel person) {
		
		if(person !=  null)
    	{
			
		   personsRepository.save(person);
     	   return ResponseEntity.ok("la personne est ajoutée avec succès.");
    	}
	    else 
	    {
	        return ResponseEntity.notFound().build();
	    }
	}

	// supprimer une personne 
	public ResponseEntity<String>  removePerson(String firstName, String lastName) {

		if(firstName != null && lastName != null)
    	{
			PersonsModel personToMDelet = personsRepository.findByfirstName(firstName, lastName);
			personsRepository.deletePerson(personToMDelet);
			return ResponseEntity.ok("la personne est supprimée avec succès.");
    	}
	    else 
	    {
	        return ResponseEntity.notFound().build();
	    }
	}

	//modifier une personne
	public ResponseEntity<String> updatePerson(PersonsModel  newPreson) 
	{
		
		if(newPreson != null )
    	{
			personsRepository.updatePerson(newPreson);
     	   return ResponseEntity.ok("Les informations de la personne sont mises à jour avec succès.");
    	}
	    else 
	    {
	        return ResponseEntity.notFound().build();
	    }
	}

}
