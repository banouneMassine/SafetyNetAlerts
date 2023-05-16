package com.safetyNet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetyNet.model.PersonsModel;
import com.safetyNet.repository.PersonsRepository;

import lombok.Data;

@Data
@Service
public class PersonsService
{
	@Autowired
	PersonsRepository personsRepository;
	
	public List<PersonsModel> getPersons() {
        return personsRepository.findAll();
    }
	
	public PersonsModel getPerson(String firstName)
	{
		return personsRepository.findByfirstName(firstName);
	}
	// ajouter une personne au fichier JSON 
	public String addPerson (PersonsModel person)
	{
		return personsRepository.save(person);
	}

}
