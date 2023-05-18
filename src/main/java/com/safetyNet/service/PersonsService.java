package com.safetyNet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String addPerson(PersonsModel person) {
		return personsRepository.save(person);
	}

	// supprimer une personne 
	public void removePerson(String firstName, String lastName) {

		PersonsModel personToMDelet = personsRepository.findByfirstName(firstName, lastName);
		personsRepository.deletePerson(personToMDelet);
	}

	//modifier une personne
	public void updatePerson(PersonsModel  newPreson) 
	{
		personsRepository.updatePerson(newPreson);
	}

}
