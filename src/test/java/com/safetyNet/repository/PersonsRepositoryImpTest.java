package com.safetyNet.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetyNet.model.PersonsModel;

@SpringBootTest
class PersonsRepositoryImpTest {

	@Autowired
	PersonsRepository personsRepositoryImp;
	
	@Test
	@DisplayName("Tester que la liste des personnes est bien récupérée")
	void findAll_testTheRecoveryOfAllPeople() {
		//GIVEN
	        	
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
    		

		//WHENE
		PersonsModel actualPerson =  personsRepositoryImp.findByName(firstName, lastName);
		//THEN
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
		//WHENE
		List<PersonsModel> listeDesPersonnes =  personsRepositoryImp.findByAdresse(adresse);
		//THEN
		assertThat(listeDesPersonnes).isNotEmpty();
	}
	
	@Test
	@DisplayName("Tester que quand je saisi adresse inexistant alors je récupère une liste vide")
	void findByAdresse_testThatWhenEnterAdresse_thenRetrieveTheEmptyList()
	{
		//GIVEN
    	String adresse = "adresse inexistant";
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
		person.setAddress("112 Steppes Pl");
		person.setCity("Culver");
		person.setEmail("tcoop@ymail.com");
		person.setPhone("841-874-6874");
		person.setZip(97451);
		
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
		personAdded.setLastName("Massine");
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
	}
	
	@Test
	@Disabled
	@DisplayName("Tester que si le format de la personne n'est pas bonne alors l'ajout de cette personne est rejetée et renvoi null")
	void save_whenePersonInBadFormat_TheneReturnNull()
	{
		//GIVEN
		PersonsModel personAdded = new PersonsModel();
		
		
		//WHEN
		PersonsModel personReturn = personsRepositoryImp.save(personAdded);
		
		//THEN
		assertThat(personReturn).isNull();
		
	}
	
	
	@Test
	@DisplayName("Tester la suppression d'une personne ")
	void deletePerson_whenePersonInList_theneDeleteIt()  // a revoir 
	{

		//GIVEN
			/*PersonsModel personToDelete = new PersonsModel();
			personToDelete.setFirstName("Jacob");
			personToDelete.setLastName("Boyd");
			personToDelete.setAddress("1509 Culver St");
			personToDelete.setCity("Culver");
			personToDelete.setEmail("drk@email.com");
			personToDelete.setPhone("841-874-6513");
			personToDelete.setZip(97451);*/
			
		PersonsModel personToDelete = personsRepositoryImp.findByName("Jacob" ,"Boyd" );
			     	
		//WHENE 
			PersonsModel personDelete = personsRepositoryImp.deletePerson(personToDelete);
		
			
		//THEN
			assertThat(personDelete).isEqualTo(personToDelete);
				
	}
	
	@Test
	@DisplayName("Tester la suppression d'une personne qui n'existe pas dans la list")
	void deletePerson_whenePersonNotInList_thenReturnNull()  // a revoir 
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
		personToUpdate.setAddress("nouvelle adresse");
		personToUpdate.setCity("nouvelle city");
		personToUpdate.setEmail("drk@email.com");
		personToUpdate.setPhone("841-874-6513");
		personToUpdate.setZip(97451);
		
		//WHENE
		PersonsModel newPerson = personsRepositoryImp.updatePerson(personToUpdate);
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
