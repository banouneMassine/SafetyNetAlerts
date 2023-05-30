package com.safetyNet.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetyNet.model.PersonsModel;

@Repository
public class PersonsRepositoryImp implements PersonsRepository {
	private List<PersonsModel> personsList = new ArrayList<>();

	public void addPerson(PersonsModel person) {
		personsList.add(person);
	}

	@Override
	public List<PersonsModel> findAll() {
		
		return this.personsList;
	}

	@Override
	public PersonsModel findByfirstName(String firstName , String lastName) {

		for (PersonsModel person : this.personsList) {
			if (person.firstName.equalsIgnoreCase(firstName) && person.lastName.equalsIgnoreCase(lastName)) {
				return person;
			}
		}
		return null;
	}

	// ajouter une personne au fichier JSON
	@Override
	public PersonsModel save(PersonsModel person) {

		 if (this.personsList.add(person))
		 {
			 return person; 
		 }
		 return null ; 
		
	}
	

	// supprimer une personne du fichier JSON
	@Override
	public PersonsModel deletePerson(PersonsModel personToMDelet) 
	{
		if(this.personsList.remove(personToMDelet))
		{
			return personToMDelet;
		}
		return null ; 
	}
	
	// modifier une personne du fichier JSON
	@Override
	public PersonsModel updatePerson(PersonsModel newPreson) 
	{
			PersonsModel personSearche= this.findByfirstName(newPreson.firstName, newPreson.lastName);
			if (personSearche != null)
			{
				personSearche.address=newPreson.address;
				personSearche.city=newPreson.city;
				personSearche.zip=newPreson.zip;
				personSearche.email=newPreson.email;
				personSearche.phone=newPreson.phone;
				return newPreson ; 
			}
			
			return null ;
			
	}

}
