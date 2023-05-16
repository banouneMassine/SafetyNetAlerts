package com.safetyNet.repository;

import java.util.List;

import com.safetyNet.model.PersonsModel;

public interface PersonsRepository {
	
	//CRUD
	public List<PersonsModel> findAll();// Get ALL persons
	public PersonsModel findByfirstName(String firstName);// Get one person
	public String save(PersonsModel person); // Post one person
	public void putPerson();// MAJ one person
	public void deletePerson(); // Delete one person
	
	//autres methodes
	public void addPerson(PersonsModel person);
}
