package com.safetyNet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.safetyNet.DTO.PersonsDTO;
import com.safetyNet.exceptions.PersonIntrovableExeption;
import com.safetyNet.model.PersonsModel;
import com.safetyNet.repository.PersonsRepository;




@Service
public class PersonsService {
	@Autowired
	PersonsRepository personsRepository;

	public List<PersonsDTO> getPersons() throws PersonIntrovableExeption {
		if(personsRepository.findAll().isEmpty()) throw new PersonIntrovableExeption("La liste des personnes est vide");
		List<PersonsDTO> listPersonsDTO = new ArrayList<>();
		for(PersonsModel person : personsRepository.findAll())
		{
			listPersonsDTO.add(this.convertToDTO(person));
		}
		
		return listPersonsDTO;
	}

	public PersonsDTO getPerson(String firstName, String lastName) throws PersonIntrovableExeption {
		if(personsRepository.findByfirstName(firstName, lastName) == null) throw new PersonIntrovableExeption("La personne " +firstName+ " "+ lastName + " est introuvable");
		return this.convertToDTO(personsRepository.findByfirstName(firstName, lastName))  ;
	}

	// ajouter une personne 
	public PersonsDTO addPerson(PersonsModel person) {
		
		if(person !=  null)
    	{	
			return   this.convertToDTO(personsRepository.save(person));
     	 
    	}
		return null ;
	  
	}

	// supprimer une personne 
	public PersonsDTO removePerson(String firstName, String lastName) throws PersonIntrovableExeption {

		if(firstName != null && lastName != null)
    	{
			PersonsModel personToMDelet = personsRepository.findByfirstName(firstName, lastName);
			if(personToMDelet == null) throw new PersonIntrovableExeption("La personne " +firstName+ " "+ lastName + " est introuvable");
			return	this.convertToDTO(personsRepository.deletePerson(personToMDelet));
    	}
		return null ;
	}

	//modifier une personne
	public PersonsDTO updatePerson(PersonsModel  newPreson) throws PersonIntrovableExeption 
	{
		
		if(newPreson != null )
    	{
			
			if(personsRepository.updatePerson(newPreson) == null) throw new PersonIntrovableExeption("La personne " +newPreson.firstName+ " "+ newPreson.lastName + " est introuvable");
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
