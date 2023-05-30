package com.safetyNet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.safetyNet.exceptions.PersonIntrovableExeption;
import com.safetyNet.model.PersonsModel;
import com.safetyNet.repository.PersonsRepository;

import lombok.Data;

@Data
@Service
public class PersonsService {
	@Autowired
	PersonsRepository personsRepository;

	public List<PersonsModel> getPersons() throws PersonIntrovableExeption {
		if(personsRepository.findAll().isEmpty()) throw new PersonIntrovableExeption("La liste des personnes est vide");
		return personsRepository.findAll();
	}

	public PersonsModel getPerson(String firstName, String lastName) throws PersonIntrovableExeption {
		if(personsRepository.findByfirstName(firstName, lastName) == null) throw new PersonIntrovableExeption("La personne " +firstName+ " "+ lastName + " est introuvable");
		return personsRepository.findByfirstName(firstName, lastName);
	}

	// ajouter une personne 
	public PersonsModel addPerson(PersonsModel person) {
		
		if(person !=  null)
    	{	
			return   personsRepository.save(person);
     	 
    	}
		return null ;
	  
	}

	// supprimer une personne 
	public PersonsModel removePerson(String firstName, String lastName) throws PersonIntrovableExeption {

		if(firstName != null && lastName != null)
    	{
			PersonsModel personToMDelet = personsRepository.findByfirstName(firstName, lastName);
			if(personToMDelet == null) throw new PersonIntrovableExeption("La personne " +firstName+ " "+ lastName + " est introuvable");
			return	personsRepository.deletePerson(personToMDelet);
    	}
		return null ;
	}

	//modifier une personne
	public PersonsModel updatePerson(PersonsModel  newPreson) throws PersonIntrovableExeption 
	{
		
		if(newPreson != null )
    	{
			
			if(personsRepository.updatePerson(newPreson) == null) throw new PersonIntrovableExeption("La personne " +newPreson.firstName+ " "+ newPreson.lastName + " est introuvable");
     	   return personsRepository.updatePerson(newPreson);
    	}
		return null ; 
	    
	}

}
