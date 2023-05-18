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
	public String save(PersonsModel person) {

		this.personsList.add(person);
		return "La personne " + person.firstName + " " + person.lastName + " est ajoutée a la liste ";
	}
	

	// supprimer une personne du fichier JSON
	@Override
	public void deletePerson(PersonsModel personToMDelet) 
	{
		this.personsList.remove(personToMDelet);
	}
	
	// modifier une personne du fichier JSON
	@Override
	public void updatePerson(PersonsModel newPreson) 
	{
			PersonsModel personSearche= this.findByfirstName(newPreson.firstName, newPreson.lastName);
			personSearche.address=newPreson.address;
			personSearche.city=newPreson.city;
			personSearche.zip=newPreson.zip;
			personSearche.email=newPreson.email;
			personSearche.phone=newPreson.phone;
	}

}
