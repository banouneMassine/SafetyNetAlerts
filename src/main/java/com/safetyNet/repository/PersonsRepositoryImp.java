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
	public PersonsModel findByName(String firstName , String lastName) {

		for (PersonsModel person : this.personsList) {
			if (person.getFirstName().equalsIgnoreCase(firstName) && person.getLastName().equalsIgnoreCase(lastName)) {
				return person;
			}
		}
		return null;
	}
	
	@Override
	public List<PersonsModel> findByAdresse( String adresse)
	{
		List<PersonsModel> listDespersonnesByAdresse = new ArrayList<>();
		for (PersonsModel person : this.personsList) {
			if (person.getAddress().equalsIgnoreCase(adresse)) {
				listDespersonnesByAdresse.add(person);
			}
		}
		return listDespersonnesByAdresse;
	}
	
	@Override 
	public List<PersonsModel> getFamilles( PersonsModel person)
	{
		List<PersonsModel> listDesPersonnesDeLaFamille = new ArrayList<>();
		for (PersonsModel fammille : this.personsList) {
			if (fammille.getLastName().equalsIgnoreCase(person.getLastName()) && !fammille.getFirstName().equalsIgnoreCase(person.getFirstName())) {
				 listDesPersonnesDeLaFamille.add(fammille);
			}
		}
		
		return listDesPersonnesDeLaFamille ; 	
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
			PersonsModel personSearche= this.findByName(newPreson.firstName, newPreson.lastName);
			if (personSearche != null)
			{
				personSearche.setAddress(newPreson.getAddress());
				personSearche.setCity(newPreson.getCity());
				personSearche.setZip(newPreson.getZip());
				personSearche.setEmail(newPreson.getEmail());
				personSearche.setPhone(newPreson.getPhone());
				return newPreson ; 
			}
			
			return null ;
			
	}

}
