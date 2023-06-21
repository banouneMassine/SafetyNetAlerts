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
 
	public PersonsDTO getPerson(String firstName, String lastName) throws PersonIntrovableExeption  {
		
		
		PersonsModel myPerson = personsRepository.findByName(firstName, lastName);
		if(myPerson == null )
		{
			logger.error("La personne " +firstName+ " "+ lastName + " est introuvable");
			throw new PersonIntrovableExeption("La personne " +firstName+ " "+ lastName + " est introuvable");
	    }
		logger.info("Récuperer la personne  " + firstName +" " +lastName);
		return this.convertToDTO(myPerson)  ;
		
	}

	// ajouter une personne 
	public PersonsDTO addPerson(PersonsModel person) throws PersonIntrovableExeption {

		if(person == null )
		{
			logger.error("La personne ne peut pas etre ajoutée");
			throw new PersonIntrovableExeption("La personne n'a pas pu etre ajouté");
		}else
		{
			logger.info("ajouter la personne " + person.getFirstName() +" " +person.getLastName());
			return   this.convertToDTO(personsRepository.save(person));
		}
		
	} 

	// supprimer une personne 
	public PersonsDTO removePerson(String firstName, String lastName) throws PersonIntrovableExeption {

			PersonsModel personToDelet = personsRepository.findByName(firstName, lastName);
			if(personToDelet == null) 
				{
					logger.error("La personne "+firstName+" "+lastName +" est introuvable");
					throw new PersonIntrovableExeption("La personne " +firstName+ " "+ lastName + " est introuvable");
				}
			logger.info("Supprimer la personne " + firstName +" " +lastName);
			return	this.convertToDTO(personsRepository.deletePerson(personToDelet));
    	
		
		
	}

	//modifier une personne
	public PersonsDTO updatePerson(PersonsModel  newPreson) throws PersonIntrovableExeption 
	{	
			if(personsRepository.updatePerson(newPreson) == null) 
				{
					logger.error("La personne "+newPreson.getFirstName()+" "+newPreson.getLastName() +" est introuvable");
					throw new PersonIntrovableExeption("La personne " +newPreson.firstName+ " "+ newPreson.lastName + " est introuvable");
				}
			logger.info("Modifier la personne " + newPreson.getFirstName() +" " +newPreson.getLastName());
			return this.convertToDTO(personsRepository.updatePerson(newPreson));
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
