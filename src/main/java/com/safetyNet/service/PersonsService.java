package com.safetyNet.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.safetyNet.DTO.PersonsDTO;
import com.safetyNet.exceptions.PersonIntrovableExeption;
import com.safetyNet.model.PersonsModel;
import com.safetyNet.repository.PersonsRepository;




@Service
public class PersonsService {
	
	private static final Logger logger = LogManager.getLogger("PersonsService");
	@Autowired
	PersonsRepository personsRepository;
	
	public List<PersonsDTO> getPersons()  {
		
		List<PersonsDTO> listPersonsDTO = new ArrayList<>();
		for(PersonsModel person : personsRepository.findAll())
		{
			listPersonsDTO.add(this.convertToDTO(person));
		}
		logger.info("Récuperer la liste des personnes");
		return listPersonsDTO;
		
	}

	public PersonsDTO getPerson(String firstName, String lastName)  {
		
		logger.info("Récuperer la personne  " + firstName +" " +lastName);
		return this.convertToDTO(personsRepository.findByName(firstName, lastName))  ;
	}

	// ajouter une personne 
	public PersonsDTO addPerson(PersonsModel person) {
		
		if(person !=  null)
    	{	
			logger.info("ajouter la personne " + person.getFirstName() +" " +person.getLastName());
			return   this.convertToDTO(personsRepository.save(person));
     	 
    	}
		return null ;
	  
	}

	// supprimer une personne 
	public PersonsDTO removePerson(String firstName, String lastName) throws PersonIntrovableExeption {

		if(firstName != null && lastName != null)
    	{
			PersonsModel personToMDelet = personsRepository.findByName(firstName, lastName);
			if(personToMDelet == null) 
				{
					logger.error("La personne "+firstName+" "+lastName +" est introuvable");
					throw new PersonIntrovableExeption("La personne " +firstName+ " "+ lastName + " est introuvable");
				}
			logger.info("Supprimer la personne " + firstName +" " +lastName);
			return	this.convertToDTO(personsRepository.deletePerson(personToMDelet));
    	}
		return null ;
	}

	//modifier une personne
	public PersonsDTO updatePerson(PersonsModel  newPreson) throws PersonIntrovableExeption 
	{
		
		if(newPreson != null )
    	{
			
			if(personsRepository.updatePerson(newPreson) == null) 
				{
					logger.error("La personne "+newPreson.getFirstName()+" "+newPreson.getLastName() +" est introuvable");
					throw new PersonIntrovableExeption("La personne " +newPreson.firstName+ " "+ newPreson.lastName + " est introuvable");
				}
			logger.info("Modifier la personne " + newPreson.getFirstName() +" " +newPreson.getLastName());
     	   return this.convertToDTO(personsRepository.updatePerson(newPreson));
    	}
		return null ; 
	    
	}
	
	public PersonsDTO convertToDTO(PersonsModel personsModel)
	{
		PersonsDTO personsDTO = new PersonsDTO();
		personsDTO.setFirstName(personsModel.getFirstName()) ;
		personsDTO.setLastName( personsModel.getLastName());
		personsDTO.setAddress( personsModel.getAddress());
		personsDTO.setCity( personsModel.getCity());
		personsDTO.setEmail( personsModel.getEmail());
		personsDTO.setPhone( personsModel.getPhone());
		personsDTO.setZip( personsModel.getZip());
		return personsDTO ; 
	}

}
