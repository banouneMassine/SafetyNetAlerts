package com.safetyNet.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import com.safetyNet.model.PersonsModel;

@ExtendWith(MockitoExtension.class)
class PersonsRepositoryImpTest {

	@InjectMocks
	PersonsRepositoryImp personsRepositoryImp;
	
	@Test
	@DisplayName("Tester que la liste des personnes est bien récupérée")
	void findAll_testTheRecoveryOfAllPeople() {
		//GIVEN
		personsRepositoryImp.addPerson(new PersonsModel());   	
		//WHENE
		List<PersonsModel> listeDesPersonnes =  personsRepositoryImp.findAll();
		//THEN
		assertThat(listeDesPersonnes).isNotEmpty();
	}
	
	@Test
	@DisplayName("Tester que quand je saisi un nom alors je récupère la personne")
	void findByName_testThatWhenEnterName_thenRetrieveThePerson()
	{
		//GIVEN
    		String firstName = "John";
    		String lastName = "Boyd";
    		
    		PersonsModel personExpected = new PersonsModel();
    		personExpected.setFirstName("John");
    		personExpected.setLastName("Boyd");
    		personExpected.setAddress("1509 Culver St");
    		personExpected.setCity("Culver");
    		personExpected.setEmail("jaboyd@email.com");
    		personExpected.setPhone("841-874-6512");
    		personExpected.setZip(97451);
    		
    		personsRepositoryImp.save(personExpected);

		//WHENE
		PersonsModel actualPerson =  personsRepositoryImp.findByName(firstName, lastName);
		//THEN
		assertThat(personsRepositoryImp.findAll().size()).isEqualTo(1);
		assertThat(actualPerson).usingRecursiveComparison().isEqualTo(personExpected);
	}
	
	
	@DisplayName("Tester que quand je saisi un nom qui n'existe pas alors je récupère une valeur null")
	@ParameterizedTest(name = "{0} + {1} doit retourner null")
	@CsvSource({ "a,b", "John,b", "a,Boyd" })
	void findByName_testThatWhenNameThatDoesntExist_thenRetrieveNull(String arg1, String arg2)
	{
		//GIVEN
    	String firstName = arg1;
    	String lastName = arg2;   	
		//WHENE
		PersonsModel actualPerson =  personsRepositoryImp.findByName(firstName, lastName);
		//THEN
		assertThat(actualPerson).isNull();
	}
	

	@Test
	@DisplayName("Tester que quand je saisi une adresse alors je récupère la liste des  personnes correspondante")
	void findByAdresse_testThatWhenEnterAdresse_thenRetrieveTheListOfPersons()
	{
		//GIVEN
    	String adresse = "1509 Culver St";
    	
    	PersonsModel personExpected1 = new PersonsModel();
    	personExpected1.setFirstName("John");
    	personExpected1.setLastName("Boyd");
    	personExpected1.setAddress("1509 Culver St");
		
    	personsRepositoryImp.save(personExpected1);
    	
    	PersonsModel personExpected2 = new PersonsModel();
    	personExpected2.setFirstName("Lionel");
    	personExpected2.setLastName("Messi");
    	personExpected2.setAddress("1509 Culver St");
    	
    	personsRepositoryImp.save(personExpected2);
    	
    	PersonsModel personExpected3 = new PersonsModel();
    	personExpected3.setFirstName("David");
    	personExpected3.setLastName("De Gea");
    	personExpected3.setAddress("01  Manchester united");
    	
    	personsRepositoryImp.save(personExpected3);
		//WHENE
		List<PersonsModel> listeDesPersonnes =  personsRepositoryImp.findByAdresse(adresse);
		//THEN
		assertThat(listeDesPersonnes.size()).isEqualTo(2);
	}
	
	@Test
	@DisplayName("Tester que quand je saisi adresse inexistant alors je récupère une liste vide")
	void findByAdresse_testThatWhenEnterAdresse_thenRetrieveTheEmptyList()
	{
		//GIVEN
    	String adresse = "adresse inexistant";
    	
    	PersonsModel personExpected1 = new PersonsModel();
    	personExpected1.setFirstName("John");
    	personExpected1.setLastName("Boyd");
    	personExpected1.setAddress("1509 Culver St");
		
    	personsRepositoryImp.save(personExpected1);
		//WHENE
		List<PersonsModel> listeDesPersonnes =  personsRepositoryImp.findByAdresse(adresse);
		//THEN
		assertThat(listeDesPersonnes).isEmpty();
	}
	
	@Test
	@DisplayName("Test la récupération de la famille d'une personne")
	void getFamilles_testThatWhenEnterPerson_TheneRuternfamilly()
	{
		//Given
		PersonsModel person = new PersonsModel();
		person.setFirstName("Tony");
		person.setLastName("Cooper");
		personsRepositoryImp.save(person);
		
		PersonsModel person2 = new PersonsModel();
		person2.setFirstName("Lily");
		person2.setLastName("Cooper");
		personsRepositoryImp.save(person2);
		
		String prenomDeLaPersonneDeLaFamille = "Lily";
		
		//WHENE 
		List<PersonsModel> listeDesPersonnesdeLaFamille =  personsRepositoryImp.getFamilles(person);
		
		//THEN
		assertThat(listeDesPersonnesdeLaFamille.get(0).getFirstName()).isEqualTo(prenomDeLaPersonneDeLaFamille);
	}
	
	
	
	
	@Test
	@DisplayName("Tester l'ajout d'une personne dans la liste de personnes")
	void save_testAddPersonInListOfPerson()
	{
		//GIVEN
		PersonsModel personAdded = new PersonsModel();
		personAdded.setFirstName("Banoune");
		personAdded.setLastName("djafar");
		personAdded.setAddress("8 rue jacque ange gabriel");
		personAdded.setCity("Bejaia");
		personAdded.setEmail("massine@gemail.com");
		personAdded.setPhone("000-111-2222");
		personAdded.setZip(75000);
	
		//WHEN
		PersonsModel personReturn = personsRepositoryImp.save(personAdded);
		
		//THEN
		assertThat(personReturn).usingRecursiveComparison().isEqualTo(personAdded);
		assertThat(personsRepositoryImp.findAll()).contains(personAdded);
		assertThat(personsRepositoryImp.findAll().size()).isEqualTo(1);
	}
	

	
	@Test
	@DisplayName("Tester la suppression d'une personne ")
	void deletePerson_whenePersonInList_theneDeleteIt()  // a revoir 
	{

		//GIVEN
			PersonsModel personToDelete = new PersonsModel();
			personToDelete.setFirstName("Jacob");
			personToDelete.setLastName("Boyd");
			personToDelete.setAddress("1509 Culver St");
		
			personsRepositoryImp.save(personToDelete);
			
		// PersonsModel personToDelete = personsRepositoryImp.findByName("Jacob" ,"Boyd" );
			     	
		//WHENE 
			PersonsModel personDelete = personsRepositoryImp.deletePerson(personToDelete);
		
			
		//THEN
			assertThat(personDelete).isEqualTo(personToDelete);
			assertThat(personsRepositoryImp.findAll().size()).isEqualTo(0);
				
	}
	
	@Test
	@DisplayName("Tester la suppression d'une personne qui n'existe pas dans la list")
	void deletePerson_whenePersonNotInList_thenReturnNull() 
	{
		//GIVEN
			PersonsModel personToDelete = new PersonsModel();
			personToDelete.setFirstName("massine");
			personToDelete.setLastName("Boyd");
			personToDelete.setAddress("1509 Culver St");
			personToDelete.setCity("Culver");
			personToDelete.setEmail("drk@email.com");
			personToDelete.setPhone("841-874-6513");
			personToDelete.setZip(97451);
			
			
		//WHENE 
			PersonsModel personDelete = personsRepositoryImp.deletePerson(personToDelete);
		
			
		//THEN
			assertThat(personDelete).isNull();;		
	}
	
	@Test
	@DisplayName("Tester la modification d'une personne")
	void updatePerson_whenePersonInList_theneUpdateIt()
	{
		//GIVEN
		PersonsModel personToUpdate = new PersonsModel();
		personToUpdate.setFirstName("Tenley");
		personToUpdate.setLastName("Boyd");
		personToUpdate.setAddress("ancienne adresse");
		personToUpdate.setCity("ancienne city");
		personToUpdate.setEmail("ancienne@email.com");
		personToUpdate.setPhone("841-874-6513");
		personToUpdate.setZip(90000);
		
		personsRepositoryImp.save(personToUpdate);
		
		PersonsModel newPersonne = new PersonsModel();
		newPersonne.setFirstName("Tenley");
		newPersonne.setLastName("Boyd");
		newPersonne.setAddress("nouvelle adresse");
		newPersonne.setCity("nouvelle city");
		newPersonne.setEmail("nouvelle@email.com");
		newPersonne.setPhone("841-874-6513");
		newPersonne.setZip(91234);
		
		//WHENE
		PersonsModel newPerson = personsRepositoryImp.updatePerson(newPersonne);
		//THEN
		
		assertThat(newPerson.getAddress()).isEqualTo("nouvelle adresse");
		assertThat(newPerson.getCity()).isEqualTo("nouvelle city");
		
	}
	
	@Test
	@DisplayName("Tester la modification d'une personne qui n'existe pas dans la list")
	void updatePerson_whenePersonNotInList_thenReturnNull()
	{
		//GIVEN
		PersonsModel personToUpdate = new PersonsModel();
		personToUpdate.setFirstName("massine");
		personToUpdate.setLastName("Boyd");
		personToUpdate.setAddress("nouvelle adresse");
		personToUpdate.setCity("nouvelle city");
		personToUpdate.setEmail("drk@email.com");
		personToUpdate.setPhone("841-874-6513");
		personToUpdate.setZip(97451);
		
		//WHENE
		PersonsModel newPerson = personsRepositoryImp.updatePerson(personToUpdate);
		//THEN
		assertThat(newPerson).isNull() ; 
		
	}
	
}
