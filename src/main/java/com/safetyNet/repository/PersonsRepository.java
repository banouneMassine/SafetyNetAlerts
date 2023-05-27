package com.safetyNet.repository;

import java.util.List;

import com.safetyNet.model.PersonsModel;

public interface PersonsRepository {
	
	//CRUD
	public List<PersonsModel> findAll();// Get ALL persons
	public PersonsModel findByfirstName(String firstName , String lastName);// Get one person
	public String save(PersonsModel person); // Post one person
	public PersonsModel updatePerson(PersonsModel personToModify);// MAJ one person
	public void deletePerson(PersonsModel personToDelete); // Delete one person
	
	//autres methodes
	public void addPerson(PersonsModel person);
}
