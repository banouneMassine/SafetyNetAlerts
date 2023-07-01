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

/**
 * Cette class traite les action du CRUD pour l'entite personsModel.
 */

@Service
public class PersonsService {
	
	private static final Logger logger = LogManager.getLogger("PersonsService");
	@Autowired
	PersonsRepository personsRepository;
	/**
     * Cette méthode recupere la liste des personnes.
     * @param /.
     * @return listPersonsDTO = la liste des personnes.
     */
	public List<PersonsDTO> getPersons()  {
		
		List<PersonsDTO> listPersonsDTO = new ArrayList<>();
		for(PersonsModel person : personsRepository.findAll())
		{
			listPersonsDTO.add(this.convertToDTO(person));
		}
		logger.info("Récuperer la liste des personnes");
		return listPersonsDTO;
		  
	} 
 
	/**
     * Cette méthode recupere une personne via son nom / prenom.
     * @param firstName : prenom de la personne
     * @param lastName : nom de la personne 
     * @return PersonsDTO = la personne recherechee.
     */
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

	/**
     * Cette méthode ajoute une personne a la liste des personnes.
     * @param person : la personne a ajoutee
     * @return PersonsDTO = la personne DTO qui a ete ajoutee.
     */
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

	/**
     * Cette méthode supprime une personne de la liste des personnes.
     * @param firstName : prenom de la personne
     * @param lastName : nom de la personne 
     * @return PersonsDTO = la personne DTO qui a ete supprimee.
     */
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

	/**
     * Cette méthode modifiee une personne .
     * @param newPreson : la personne a modifier avec de nouvelles donnees 
     * @return PersonsDTO = la personne DTO qui a ete modifiee.
     */
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
	
	/**
     * Cette méthode convertie une instance PersonsModel en une instance  PersonsDTO.
     * @param PersonsModel 
     * @return PersonsDTO 
     */
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
