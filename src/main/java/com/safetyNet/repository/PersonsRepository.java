package com.safetyNet.repository;

import java.util.List;

import com.safetyNet.model.PersonsModel;

public interface PersonsRepository {
	
	//CRUD
	public List<PersonsModel> findAll();// Get ALL persons
	public PersonsModel findByName(String firstName , String lastName);// Get one person
	public PersonsModel save(PersonsModel person); // Post one person
	public PersonsModel updatePerson(PersonsModel personToModify);// MAJ one person
	public PersonsModel deletePerson(PersonsModel personToDelete); // Delete one person
	public List<PersonsModel> findByAdresse(String adresse);
	//autres methodes
	public void addPerson(PersonsModel person);
	List<PersonsModel> getFamilles(PersonsModel person);

	
	
}
