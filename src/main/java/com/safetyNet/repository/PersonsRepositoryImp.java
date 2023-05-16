package com.safetyNet.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetyNet.model.PersonsModel;


@Repository
public class PersonsRepositoryImp implements  PersonsRepository
{
	private List<PersonsModel> personsList = new ArrayList<>();
	

    
    public void addPerson(PersonsModel person)
    {
    	personsList.add(person);
    }
   

	@Override
	public List<PersonsModel> findAll() {
	
		return this.personsList;
	}

	@Override
	public PersonsModel findByfirstName(String firstName) {
		System.out.println("repo " +  firstName );
		for(PersonsModel person : this.personsList )
		{
			if( person.firstName.equalsIgnoreCase(firstName) )
			{
				System.out.println("ok");
				return person; 
			}
		}
		return null;
	}
	
	
	// ajouter une personne au fichier JSON 
	@Override
	public String save(PersonsModel person) {
		
		this.personsList.add(person);
		return "La personne "+ person.firstName + " " + person.lastName + " est ajoutée a la liste ";
	}

	@Override
	public void putPerson() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson() {
		// TODO Auto-generated method stub
		
	}


}
